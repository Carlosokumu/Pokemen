package com.carlos.pokemen.screens

import android.graphics.drawable.BitmapDrawable
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.palette.graphics.Palette
import coil.ImageLoader
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.request.SuccessResult
import coil.size.Size
import com.carlos.database.entity.PokemonEntity
import com.carlos.database.mapper.asDomain
import com.carlos.model.Pokemon
import com.carlos.pokemen.navigation.MainActions
import com.carlos.pokemen.ui.theme.Typography
import com.carlos.pokemen.utils.PokemonUtils
import com.carlos.pokemen.utils.isScrolledToEnd
import com.carlos.pokemen.viewmodels.HomeViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PokemonListView(homeViewModel: HomeViewModel, state: LazyGridState, mainActions: MainActions) {
    val scrollState = rememberLazyGridState()

    val pokemons = homeViewModel.pokemon.value.collectAsLazyPagingItems()


    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        state =  scrollState
    ) {

        items(pokemons.itemCount) { index ->
            val context = LocalContext.current
            val loader = ImageLoader(context)


            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(PokemonUtils.getImageUrl(url = pokemons[index]!!.url))
                    .allowHardware(false)
                    .size(Size.ORIGINAL) // Set the target size to load the image at.
                    .build()
            )


            if (painter.state is AsyncImagePainter.State.Success) {
                var color = 123342
                LaunchedEffect(key1 = painter) {
                    launch {
                        val result =
                            (loader.execute(painter.request) as SuccessResult).drawable
                        val bitmap = (result as BitmapDrawable).bitmap
                        val vibrant = Palette.from(bitmap)
                            .generate()
                        val dominantSwatch = vibrant.dominantSwatch?.rgb
                        if (dominantSwatch != null) {
                            homeViewModel.setColor(dominantSwatch)
                            color = dominantSwatch
                        }
                    }

                }

                PokemonCard(
                    cardBackground = Color( pokemons[index]!!.asDomain().color),
                    mainActions = mainActions,
                    pokemon = pokemons[index]!!.asDomain(),
                    index = index
                )


            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PokemonCard(cardBackground: Color, mainActions: MainActions, pokemon: Pokemon, index: Int){
    Card(
        modifier = Modifier.padding(4.dp),
        elevation = 10.dp,
        onClick = {
            mainActions.gotoDetailsScreen(pokemon.name, index,"home")
        },
        backgroundColor = cardBackground
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
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = pokemon.name,
                        style = Typography.h1.merge(),
                        color = Color.Blue,
                        fontWeight = FontWeight.Bold
                    )

                }
                SubcomposeAsyncImage(
                    model = PokemonUtils.getImageUrl(pokemon.url),
                    contentDescription = "null",
                    alignment = Alignment.Center
                ) {
                    val state = painter.state
                    if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center), color = cardBackground)
                    } else {
                        SubcomposeAsyncImageContent()
                    }
                }


            }
        }
    }
}








