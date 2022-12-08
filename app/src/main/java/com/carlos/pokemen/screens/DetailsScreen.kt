package com.carlos.pokemen.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.carlos.model.PokemonInfo
import com.carlos.model.TypeResponse
import com.carlos.pokemen.navigation.MainActions
import com.carlos.pokemen.sharedcomposable.BackButton
import com.carlos.pokemen.ui.theme.*
import com.carlos.pokemen.utils.PokemonUtils
import com.carlos.pokemen.viewmodels.DetailsViewModel
import com.example.pokedex.R
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlin.random.Random


@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun DetailsScreen(detailsViewModel: DetailsViewModel, name: String?,mainActions: MainActions) {
    LaunchedEffect(key1 = name) {
        detailsViewModel.fetchPokemonDetails(name = name!!)
    }


    var isLoading by remember { mutableStateOf(true) }

    val uiState: com.carlos.model.PokemonState by detailsViewModel.pokemonState.collectAsState((com.carlos.model.PokemonState.Loading))


    isLoading = when (uiState) {
        is com.carlos.model.PokemonState.Loading -> {
            true
        }
        is com.carlos.model.PokemonState.Error -> {
            false

        }
        is com.carlos.model.PokemonState.Result -> {
            false

        }
    }
    if (isLoading) {

        Box(contentAlignment = Center, modifier = Modifier.fillMaxSize()) {
            Loader()
        }
    } else {

        Scaffold(topBar = { TopBar(detailsViewModel.pokemonInfo.collectAsState().value,mainActions) }) {

            Column(modifier = Modifier.fillMaxSize()) {

                PokemonInfo(
                    pokemonInfo = detailsViewModel.pokemonInfo.collectAsState().value
                )
                Spacer(modifier = Modifier.size(5.dp))
                MoreInfo(pokemonInfo = detailsViewModel.pokemonInfo.collectAsState().value)
                Spacer(modifier = Modifier.size(5.dp))

            }
        }

    }


}


@Composable
fun TopBar(pokemonInfo: PokemonInfo,mainActions: MainActions) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.primary),
        verticalAlignment = CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BackButton(modifier = Modifier.padding(10.dp)) {
            mainActions.popBackStack()
        }
        Text(
            text = pokemonInfo.name,
            style = Typography.h1,
            color = Color.White,
            modifier = Modifier.padding(10.dp)
        )
        Text(
            text = PokemonUtils.getIndex(pokemonInfo.id),
            style = Typography.h1,
            color = Color.White,
            modifier = Modifier.padding(10.dp)
        )
    }
}

@Preview
@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(statusColor),
        verticalAlignment = CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BackButton(modifier = Modifier.padding(10.dp)) {
        }
        Text(
            text = "Kakuna",
            color = Color.White,
            modifier = Modifier.padding(10.dp),
            style = Typography.h1
        )
        Text(
            text = "#001",
            color = Color.White,
            modifier = Modifier.padding(10.dp),
            fontWeight = FontWeight.Bold
        )
    }
}


@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun PokemonInfo(pokemonInfo: PokemonInfo) {


    Card(
        modifier = Modifier
            .padding(4.dp),
        shape = RectangleShape,
       // backgroundColor = Color.White
    ) {
        Column(verticalArrangement = Arrangement.SpaceEvenly) {
            Text(
                text = PokemonUtils.getIndex(pokemonInfo.id),
                color = Color.Black,
                style = Typography.h3,
                modifier = Modifier.padding(start = 40.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = CenterVertically
            ) {
                Text(
                    text = pokemonInfo.name,
                    modifier = Modifier.padding(start = 40.dp),
                    color = statusColor,
                    style = Typography.h1.merge()
                )
                Spacer(modifier = Modifier.size(15.dp))

                Row {

                    RowAbilities(pokemonInfo.types)
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                LazyColumn(userScrollEnabled = false, modifier = Modifier.wrapContentHeight()) {
                    items(pokemonInfo.stats, itemContent = { stat ->


                        Row(
                            modifier = Modifier
                                .wrapContentHeight()
                                .padding(10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = CenterVertically

                        ) {
                            Text(
                                text = PokemonUtils.getAbbreviation(stat.stat.name),
                                color = Color.Black,
                                modifier = Modifier,
                                style = Typography.h2.merge()
                            )

                            LinearProgressIndicator(
                                progress = (stat.base_stat * 0.01).toFloat(),
                                color = PokemonUtils.getColor(stat.stat.name),
                                modifier = Modifier
                                    .height(5.dp)
                                    .width(150.dp)
                                    .padding(start = 10.dp)

                            )

                        }

                    })
                }




                AsyncImage(
                    model = PokemonUtils.getImageUrl(pokemonInfo.species.url),
                    modifier = Modifier
                        .align(CenterVertically)
                        .height(150.dp)
                        .wrapContentWidth()
                        .padding(10.dp),
                    contentDescription = null
                )


            }
        }
    }
}

@Preview
@Composable
fun PokemonInfo() {

    val stats = listOf("Attack,Speed,defence", "Special Attack")
    Card(
        modifier = Modifier
            .padding(4.dp),
        shape = RectangleShape,
        backgroundColor = Color.White
    ) {
        Column(verticalArrangement = Arrangement.SpaceEvenly) {
            Text(text = "#001", style = Typography.h3, modifier = Modifier.padding(start = 40.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = CenterVertically
            ) {
                Text(
                    text = "Kakuna",
                    modifier = Modifier.padding(start = 40.dp),
                    style = Typography.h1.merge(),
                    color = statusColor
                )

                Row {
                    Text(
                        text = "Poison", style = typography.body1.merge(), modifier = Modifier
                            .clip(
                                shape = RoundedCornerShape(
                                    size = 5.dp,
                                ),
                            )

                            .background(Color.Green)
                            .padding(5.dp)
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(
                        text = "Poison", style = typography.body1.merge(), modifier = Modifier
                            .clip(
                                shape = RoundedCornerShape(
                                    size = 5.dp,
                                ),
                            )

                            .background(mainColor)
                            .padding(5.dp)
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                }


            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                LazyColumn(userScrollEnabled = false, modifier = Modifier.wrapContentHeight()) {
                    items(stats) { stat ->
                        // Log.d("PROGRESS",pokemonInfo.getHpFloat(stat.base_stat).toString())
                        Row(
                            modifier = Modifier
                                .wrapContentHeight()
                                .padding(10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = CenterVertically

                        ) {
                            Text(text = "ATK", modifier = Modifier, style = Typography.h2.merge())
                            LinearProgressIndicator(
                                progress = 0.5F, color = Red900, modifier = Modifier
                                    .height(5.dp)
                                    .width(150.dp)
                                    .padding(start = 10.dp)

                            )

                        }

                    }
                }




                AsyncImage(
                    model = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
                    modifier = Modifier
                        .align(CenterVertically)
                        .height(150.dp)
                        .wrapContentWidth()
                        .padding(10.dp),
                    contentDescription = null
                )


            }
        }
    }
}


@Composable
fun MoreInfo(pokemonInfo: PokemonInfo) {
    Card(elevation = 10.dp, shape = RectangleShape, modifier = Modifier.padding(8.dp)) {
        Column(verticalArrangement = Arrangement.Center) {
            Text(
                text = "Breeding",
                color = Color.Black,
                modifier = Modifier.padding(start = 10.dp, top = 5.dp),
                style = Typography.h4.merge()
            )
            Row(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    // Text(text = "Breeding", color = Color.Black)
                    Spacer(modifier = Modifier.size(10.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(CenterHorizontally), horizontalArrangement = Arrangement.Center
                    ) {

                        Column {
                            Text(
                                text = "Height",
                                color = Color.Black,
                                modifier = Modifier
                                    .padding(5.dp)
                                    .align(CenterHorizontally),
                                style = Typography.h6.merge()
                            )
                            Card(
                                border = BorderStroke(1.dp, lightSilver),
                                modifier = Modifier
                                    .padding(10.dp)
                                    .alpha(1f),
                                backgroundColor = platinum


                            ) {
                                Row(horizontalArrangement = Arrangement.SpaceAround) {
                                    Text(
                                        text = pokemonInfo.getHeightStringMtr(),
                                        color = paleBlack,
                                        style = Typography.h6,
                                        modifier = Modifier.padding(
                                            top = 10.dp,
                                            bottom = 10.dp,
                                            start = 20.dp,
                                            end = 20.dp
                                        )
                                    )
                                    Text(
                                        text = pokemonInfo.getHeightStringFt(),
                                        color = paleBlack,
                                        style = Typography.h6,
                                        modifier = Modifier.padding(
                                            top = 10.dp,
                                            bottom = 10.dp,
                                            start = 20.dp,
                                            end = 20.dp
                                        )
                                    )
                                }
                            }

                        }
                        Column {
                            Text(
                                text = "Weight",
                                color = Color.Black,
                                style = Typography.h6.merge(),
                                modifier = Modifier
                                    .padding(5.dp)
                                    .align(CenterHorizontally)
                            )
                            Card(
                                border = BorderStroke(1.dp, lightSilver),
                                modifier = Modifier.padding(10.dp),
                                backgroundColor = platinum,
                                shape = RectangleShape
                            ) {
                                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                                    Text(
                                        text = pokemonInfo.getWeightStringKg(),
                                        color = paleBlack,
                                        style = Typography.h6,
                                        modifier = Modifier.padding(
                                            top = 10.dp,
                                            bottom = 10.dp,
                                            start = 20.dp,
                                            end = 20.dp
                                        )
                                    )
                                    Text(
                                        text = pokemonInfo.getWeightStringHm(),
                                        color = paleBlack,
                                        style = Typography.h6,
                                        modifier = Modifier.padding(
                                            top = 10.dp,
                                            bottom = 10.dp,
                                            start = 20.dp,
                                            end = 20.dp
                                        )
                                    )
                                }
                            }

                        }

                    }
                    Spacer(modifier = Modifier.size(5.dp))

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonMoves() {
    val moves =
        listOf("Sombero", "Night Move", "Dragon Move", "Elcalsic", "Saton", "Ramboz")
    Card(elevation = 10.dp, shape = RectangleShape, modifier = Modifier.padding(8.dp)) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = CenterVertically
            ) {
                Text(text = "Moves", fontSize = 14.sp, color = Color.Black)
                Button(
                    onClick = {

                    },
                    elevation = null,
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Black
                    ),
                ) {
                    Text(
                        text = "See all",
                        color = Color.White,
                        fontSize = 14.sp
                    )
                }
            }
            Spacer(modifier = Modifier.size(5.dp))
            LazyVerticalGrid(GridCells.Fixed(2), contentPadding = PaddingValues(8.dp)) {
                items(moves) { item ->
                    Card(
                        modifier = Modifier
                            .padding(4.dp)
                            .wrapContentHeight(),
                        backgroundColor = Color(
                            red = Random.nextInt(0, 255),
                            green = Random.nextInt(0, 255),
                            blue = Random.nextInt(0, 255)
                        )
                    ) {
                        Text(
                            text = item,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(24.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun MoreInfo() {
    Card(elevation = 10.dp, shape = RectangleShape, modifier = Modifier.padding(8.dp)) {
        Column(verticalArrangement = Arrangement.Center) {
            Text(
                text = "Breeding",
                color = Color.Black,
                modifier = Modifier.padding(start = 10.dp, top = 5.dp),
                style = Typography.h4.merge()
            )
            Row(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Spacer(modifier = Modifier.size(10.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(CenterHorizontally), horizontalArrangement = Arrangement.Center
                    ) {

                        Column {
                            Text(
                                text = "Height",
                                color = Color.Gray,
                                style = Typography.h6.merge(),
                                modifier = Modifier
                                    .padding(5.dp)
                                    .align(CenterHorizontally)
                            )
                            Card(
                                border = BorderStroke(1.dp, lightSilver),
                                modifier = Modifier
                                    .padding(10.dp)
                                    .alpha(1f),
                                backgroundColor = platinum


                            ) {
                                Row(horizontalArrangement = Arrangement.SpaceAround) {
                                    Text(
                                        text = "204",
                                        color = paleBlack,
                                        style = Typography.h6,
                                        modifier = Modifier.padding(
                                            top = 10.dp,
                                            bottom = 10.dp,
                                            start = 20.dp,
                                            end = 20.dp
                                        ),

                                        )
                                    Text(
                                        text = "204",
                                        color = paleBlack,
                                        modifier = Modifier.padding(
                                            top = 10.dp,
                                            bottom = 10.dp,
                                            start = 20.dp,
                                            end = 20.dp
                                        )
                                    )
                                }
                            }

                        }
                        Column {
                            Text(
                                text = "Weight",
                                color = Color.Gray,
                                style = Typography.h6.merge(),
                                modifier = Modifier
                                    .padding(5.dp)
                                    .align(CenterHorizontally)
                            )
                            Card(
                                border = BorderStroke(1.dp, lightSilver),
                                modifier = Modifier.padding(10.dp),
                                backgroundColor = platinum,
                                shape = RectangleShape
                            ) {
                                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                                    Text(
                                        text = "128KG",
                                        color = paleBlack,
                                        style = Typography.h6,
                                        modifier = Modifier.padding(
                                            top = 10.dp,
                                            bottom = 10.dp,
                                            start = 20.dp,
                                            end = 20.dp
                                        )
                                    )
                                    Text(
                                        text = "204",
                                        color = paleBlack,
                                        style = Typography.h6,
                                        maxLines = 1,
                                        modifier = Modifier.padding(
                                            top = 10.dp,
                                            bottom = 10.dp,
                                            start = 20.dp,
                                            end = 20.dp
                                        )
                                    )
                                }
                            }

                        }

                    }
                    Spacer(modifier = Modifier.size(5.dp))

                }
            }
        }
    }
}

@Composable
fun RowAbilities(abilities: List<TypeResponse>) {

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(end = 10.dp)
    ) {
        items(items = abilities, itemContent = { item ->
            Text(
                text = item.type.name, style = typography.body1.merge(), modifier = Modifier
                    .clip(
                        shape = RoundedCornerShape(
                            size = 5.dp,
                        ),
                    )

                    .background(PokemonUtils.getTypeColor(item.type.name))
                    .padding(5.dp)
            )
        })
    }
}


@Composable
fun Loader() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.pokeballloading))
    LottieAnimation(composition, modifier = Modifier.size(100.dp), isPlaying = true,restartOnPlay = true)
}
