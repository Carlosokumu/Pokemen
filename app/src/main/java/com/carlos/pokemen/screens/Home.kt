package com.carlos.pokemen.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.carlos.pokemen.getImageUrl
import com.carlos.pokemen.interfaces.ToolbarState
import com.carlos.pokemen.navigation.MainActions
import com.carlos.pokemen.ui.theme.Purple700
import com.carlos.pokemen.ui.theme.statusColor
import com.carlos.pokemen.viewmodels.MainViewModel
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState
import me.onebone.toolbar.rememberCollapsingToolbarState


@Composable
fun Home(
    homeViewModel: MainViewModel = hiltViewModel(), mainActions: MainActions
) {

    val lazyScrollState = rememberLazyGridState()
    val state =  rememberCollapsingToolbarScaffoldState()
    CollapsingToolbarScaffold(
        modifier = Modifier.fillMaxSize(),
        state = state,
        scrollStrategy = ScrollStrategy.EnterAlwaysCollapsed,
        toolbar = {
            CollapsingToolbar()
        }
    )
    {
      PokemonListView(homeViewModel = homeViewModel, state = lazyScrollState, mainActions = mainActions)
    }

}




@Preview
@Composable
fun CollapsingToolbar() {
    Column(
        modifier = Modifier
            .background(statusColor),
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = "Who are you looking for?",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.size(10.dp))
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                value = "",
                shape = RoundedCornerShape(32.dp),
                singleLine = true,
                onValueChange = {},
                placeholder = {
                    Text(
                        text = "eg Kakuna",
                        color = Color.Black
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "",
                        tint = Color.Black
                    )
                },
                trailingIcon = {
                    OutlinedButton(
                        onClick = {},
                        modifier = Modifier
                            .size(24.dp),
                        shape = CircleShape,
                        border = BorderStroke(0.dp, Color.Transparent),
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = Color.White,
                            backgroundColor = Color.Black
                        )
                    ) {
                        Icon(
                            modifier = Modifier.size(20.dp),
                            imageVector = Icons.Filled.ArrowForward,
                            contentDescription = null,
                            tint = Color.Gray
                        )
                    }
                },



            )
            Spacer(modifier = Modifier.size(10.dp))

        }

    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PokemonList(mainViewModel: MainViewModel, state: LazyGridState, mainActions: MainActions) {

    mainViewModel.getPokemonList()
    val pokemons = mainViewModel.pokemon.value.collectAsLazyPagingItems()



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
                    mainActions.gotoDetailsScreen()
                },
                backgroundColor = Color.White
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
                        AsyncImage(
                            model = getImageUrl(pokemons[index]!!.url),
                            contentDescription = null
                        )
                        val context = LocalContext.current

//                        val imageLoader = ImageLoader(context)
//
//                        val request = ImageRequest.Builder(context)
//                            .transformations(RoundedCornersTransformation(12.dp.value))
//                            .data()
//                            .build()
//
//                        val imagePainter = rememberCoilPainter(
//                            request = request,
//                            imageLoader = imageLoader
//                        )
//
//                        LaunchedEffect(key1 = imagePainter) {
//                            launch {
//                                val result = (imageLoader.execute(request) as SuccessResult).drawable
//                                val bitmap = (result as BitmapDrawable).bitmap
//                                val vibrant = Palette.from(bitmap)
//                                    .generate()
//                                    .getVibrantColor(defaultColor)
//                                // do something with vibrant color
//                            }
//                        }

                    }


                }
            }
        }

    }
}







