package com.carlos.pokemen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.paging.compose.collectAsLazyPagingItems
import com.carlos.pokemen.navigation.NavGraph
import com.carlos.pokemen.screens.*
import com.carlos.pokemen.viewmodels.MainViewModel
import com.carlos.pokemen.ui.theme.PokedexTheme
import com.carlos.pokemen.ui.theme.Purple700
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




        setContent {
            PokedexTheme(darkTheme = false) {
      //          DestinationsNavHost(navGraph = NavGraphs.root)

//                val lazyScrollState = rememberLazyGridState()
//                Scaffold(
//                    modifier = Modifier
//                        .fillMaxSize(),
//                    topBar = {
//                        TopBar(lazyScrollState)
//                    }
//                )  {
//                    PokemonList(mainViewModel = hiltViewModel(), state = lazyScrollState)
//
//                }
                NavGraph()

            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PokedexTheme(darkTheme = true) {
        Greeting("Android")
    }
}

@Composable
fun GetData(mainViewModel: MainViewModel = hiltViewModel()) {
    mainViewModel.getPokemonList()
    val trendingFilms = mainViewModel.pokemon.value.collectAsLazyPagingItems()
    //trendingFilms.get(0)?.name
}

//@Composable
//fun Home(){
//    Column {
//        TopSearchBar()
//      //  PokemonList()
//    }
//}

@Composable
fun TopSearchBar(){
    Column( modifier = Modifier
        .fillMaxWidth()
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
                        painter = painterResource(id = com.example.pokedex.R.drawable.ic_search),
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


@OptIn(ExperimentalFoundationApi::class)
@Composable()
//fun PokemonList(mainViewModel: MainViewModel = hiltViewModel()) {
//
//    mainViewModel.getPokemonList()
//    val trendingFilms = mainViewModel.pokemon.value.collectAsLazyPagingItems()
//
//
//
//
//    LazyVerticalGrid(
//        columns = GridCells.Fixed(2),
//        contentPadding = PaddingValues(8.dp)
//    ) {
//
//        items(trendingFilms.itemSnapshotList.items) { item ->
//            Log.d("IMAGE_URL", getImageUrl(item.url))
//            Card(
//                modifier = Modifier.padding(4.dp),
//                elevation = 10.dp,
//                backgroundColor = Color.White
//            ) {
//                Column(
//                    modifier = Modifier.fillMaxWidth(),
//                    verticalArrangement = Arrangement.Center
//                ) {
//                    Column(
//                        modifier = Modifier
//                            .wrapContentHeight()
//                            .fillMaxWidth()
//                    ) {
//                        Row(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(top = 6.dp, start = 6.dp, end = 6.dp),
//                            horizontalArrangement = Arrangement.SpaceEvenly,
//                            verticalAlignment = Alignment.Top,
//                        ) {
//                            Text(
//                                text = item.name,
//                                color = Color.Blue,
//                                fontWeight = FontWeight.Bold
//                            )
//                            Text(
//                                text = trendingFilms.itemSnapshotList.items.indexOf(item)
//                                    .toString(),
//                                color = Color.Black,
//                                textAlign = TextAlign.Center,
//                            )
//                        }
//                        AsyncImage(
//                            model = getImageUrl(item.url),
//                            contentDescription = null
//                        )
//
//                    }
//
//
//                }
//            }
//        }
//    }
//}

fun getImageUrl(url: String): String {
    val index = url.split("/".toRegex()).dropLast(1).last()
    return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$index.png"
}
