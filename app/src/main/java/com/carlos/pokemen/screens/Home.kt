package com.carlos.pokemen.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.carlos.pokemen.getImageUrl
import com.carlos.pokemen.interfaces.ToolbarState
import com.carlos.pokemen.ui.theme.Purple700
import com.carlos.pokemen.viewmodels.MainViewModel
import com.example.pokedex.R
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Composable
fun Home(
    homeViewModel: MainViewModel = hiltViewModel(),navigator: DestinationsNavigator
){

    val lazyScrollState = rememberLazyGridState()
    Scaffold(
        topBar = {
            TopBar(lazyScrollState)
        }
    )  {
        PokemonList(state = lazyScrollState, mainViewModel = homeViewModel)

    }




}


@Composable
fun CollapsingToolbar() {
    Column( modifier = Modifier
        .background(Purple700),
        verticalArrangement = Arrangement.Center) {
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Who are you looking for?",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.size(10.dp))
            TextField(
                value = "",
                onValueChange = {

                },
                placeholder = {
                    Text(
                        text = "Search",
                        //color = primaryGray
                    )
                },

                modifier = Modifier
                    .fillMaxWidth(0.80f)
                    .background(Color.White, shape = RoundedCornerShape(8.dp))
                    .clickable {

                    },
                shape = RoundedCornerShape(size = 8.dp),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    autoCorrect = true,
                    keyboardType = KeyboardType.Text,
                ),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.White,
                    disabledTextColor = Color.White,
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.White,
                    disabledIndicatorColor = Color.White
                ),
                textStyle = TextStyle(color = Color.Black),
                maxLines = 1,
                singleLine = true,
                leadingIcon = {
                    Icon(
                        modifier = Modifier
                            .size(24.dp),
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = null,
                        tint = Color.Gray
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
                }
            )
            Spacer(modifier = Modifier.size(10.dp))

        }

    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PokemonList(mainViewModel: MainViewModel,state: LazyGridState) {

    mainViewModel.getPokemonList()
    val pokemons = mainViewModel.pokemon.value.collectAsLazyPagingItems()

    val n = rememberScrollState()


    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        state = state
    ) {
        items(pokemons.itemCount){ index ->
            Card(
                modifier = Modifier.padding(4.dp),
                elevation = 10.dp,
                onClick = {

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

                    }


                }
            }
        }

    }
}


@Composable
private fun rememberToolbarState(toolbarHeightRange: IntRange): ToolbarState {
    TODO() //Return a 'ToolbarState' implementation and preserve its internal state.
    //LazyColumn(content = )
}





