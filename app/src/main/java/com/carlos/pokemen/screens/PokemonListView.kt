package com.carlos.pokemen.screens

import android.graphics.drawable.BitmapDrawable
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.palette.graphics.Palette
import coil.ImageLoader
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.request.SuccessResult
import coil.size.Size
import com.carlos.pokemen.getImageUrl
import com.carlos.pokemen.navigation.MainActions
import com.carlos.pokemen.viewmodels.HomeViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PokemonListView(homeViewModel: HomeViewModel, state: LazyGridState, mainActions: MainActions) {
    var color: Int? = null

    val pokemons = homeViewModel.pokemon.value.collectAsLazyPagingItems()
    LaunchedEffect(key1 = 10) {
        homeViewModel.getPokemonList()
    }


    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        state = state
    ) {

        items(pokemons.itemCount) { index ->
            Card(
                modifier = Modifier.padding(4.dp),
                elevation = 10.dp,
                onClick = {
                    mainActions.gotoDetailsScreen(pokemons[index]!!.name,index)
                },
                backgroundColor =  Color.White
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Column(
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 6.dp, start = 6.dp, end = 6.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.Top,
                        ) {
                            Text(
                                text = pokemons[index]!!.name,
                                color = Color.Blue,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = index.toString(),
                                color = Color.Black,
                                textAlign = TextAlign.Center,
                            )
                        }
                        val context = LocalContext.current

                        val imageLoader = ImageLoader(context)


                        val painter = rememberAsyncImagePainter(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(getImageUrl(url = pokemons[index]!!.url))
                                .allowHardware(false)
                                .size(Size.ORIGINAL) // Set the target size to load the image at.
                                .build()
                        )
                        if (painter.state is AsyncImagePainter.State.Success) {
                            LaunchedEffect(key1 = painter) {
                                launch {
                                    val result =
                                        (imageLoader.execute(painter.request) as SuccessResult).drawable
                                    val bitmap = (result as BitmapDrawable).bitmap
                                    val vibrant = Palette.from(bitmap)
                                        .generate()
                                    color = vibrant.dominantSwatch?.rgb


                                    Log.d("COLORGEN", color.toString())
                                }

                            }

                        }



                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PokeMonView(color: Color?,homeViewModel: HomeViewModel) {
    val pokemons = homeViewModel.pokemon.value.collectAsLazyPagingItems()
    LaunchedEffect(key1 = 10) {
        homeViewModel.getPokemonList()
    }


    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),

    ) {

        items(pokemons.itemCount) { index ->
            Card(
                modifier = Modifier.padding(4.dp),
                elevation = 10.dp,
                onClick = {
                    //mainActions.gotoDetailsScreen()
                },
                backgroundColor = color ?: Color.White
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Column(
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 6.dp, start = 6.dp, end = 6.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.Top,
                        ) {
                            Text(
                                text = pokemons[index]!!.name,
                                color = Color.Blue,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = index.toString(),
                                color = Color.Black,
                                textAlign = TextAlign.Center,
                            )
                        }
                    }
                }
            }
        }
    }
}




