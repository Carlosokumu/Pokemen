package com.carlos.pokemen.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.carlos.pokemen.getImageUrl
import com.carlos.pokemen.sharedcomposable.BackButton
import com.carlos.pokemen.ui.theme.*
import com.carlos.pokemen.viewmodels.DetailsViewModel
import com.example.pokedex.R
import java.security.AllPermission
import kotlin.random.Random


@Composable
fun DetailsScreen(detailsViewModel: DetailsViewModel,name: String?,index: Int?) {
    Log.d("NamePokemon",index!!.toString())

    detailsViewModel.fetchPokemonDetails(name = "kakuna")
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(name = name!!)
        PokemonInfo()
        Spacer(modifier = Modifier.size(5.dp))
        MoreInfo()
        Spacer(modifier = Modifier.size(5.dp))
        PokemonMoves()
    }

}


@Composable
fun TopBar(name: String){
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(statusColor), verticalAlignment = CenterVertically, horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BackButton(modifier = Modifier.padding(10.dp)) {
        }
        Text(text = name,color = Color.White,modifier = Modifier.padding(10.dp))
        Text(text = "#001",color = Color.Black, modifier = Modifier.padding(10.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonInfo() {
    Card(
        modifier = Modifier
            .padding(8.dp),
        shape = RectangleShape,
        backgroundColor = Color.White
    ) {
        Column(verticalArrangement = Arrangement.SpaceEvenly) {
            Text(text = "#001", modifier = Modifier.padding(start = 40.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = CenterVertically
            ) {
                Text(text = "Kakuna",modifier = Modifier.padding(start = 40.dp),color = statusColor)

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
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier
                    .wrapContentHeight()
                    .padding(15.dp)) {
                    Text(text = "Hp")
                    Spacer(modifier = Modifier.size(5.dp))
                    LinearProgressIndicator(progress = 0.5f, color = Color.Green, modifier = Modifier
                        .height(5.dp)
                        .width(150.dp))
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(text = "Attacking")
                    Spacer(modifier = Modifier.size(5.dp))
                    LinearProgressIndicator(progress = 0.5f,color = Color.Red,modifier = Modifier
                        .height(5.dp)
                        .width(150.dp))
                    Spacer(modifier = Modifier.size(5.dp))
                    Text(text = "Defence")
                    Spacer(modifier = Modifier.size(5.dp))
                    LinearProgressIndicator(progress = 0.5f,color = Color.Yellow,modifier = Modifier
                        .height(5.dp)
                        .width(150.dp))
                    Spacer(modifier = Modifier.size(5.dp))
                    Text(text = "Speed")
                    Spacer(modifier = Modifier.size(5.dp))
                    LinearProgressIndicator(progress = 0.5f,color = gold,modifier = Modifier
                        .height(5.dp)
                        .width(150.dp))
                    Spacer(modifier = Modifier.size(10.dp))

                }
                AsyncImage(
                    model = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
                    modifier = Modifier
                        .align(CenterVertically)
                        .height(100.dp)
                        .wrapContentWidth()
                        .padding(20.dp),
                    contentDescription = null
                )



            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MoreInfo() {
    Card(elevation = 10.dp, shape = RectangleShape, modifier = Modifier.padding(8.dp)) {
        Column(verticalArrangement = Arrangement.Center) {
            Text(text = "Breeding", color = Color.Black, modifier = Modifier.padding(start = 10.dp,top = 5.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    // Text(text = "Breeding", color = Color.Black)
                    Spacer(modifier = Modifier.size(10.dp))
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .align(CenterHorizontally), horizontalArrangement = Arrangement.Center) {

                        Column {
                            Text(
                                text = "Height",
                                color = Color.Black,
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
                                        color = Color.Black,
                                        modifier = Modifier.padding(top = 10.dp, bottom = 10.dp, start = 20.dp,end = 20.dp)
                                    )
                                    Text(
                                        text = "204",
                                        color = Color.Black,
                                        modifier = Modifier.padding(top = 10.dp, bottom = 10.dp, start = 20.dp,end = 20.dp)
                                    )
                                }
                            }

                        }
                        Column {
                            Text(
                                text = "Weight",
                                color = Color.Black,
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
                                        text = "204",
                                        color = Color.Black,
                                        modifier = Modifier.padding(top = 10.dp, bottom = 10.dp, start = 20.dp,end = 20.dp)
                                    )
                                    Text(
                                        text = "204",
                                        color = Color.Black,
                                        modifier = Modifier.padding(top = 10.dp, bottom = 10.dp, start = 20.dp,end = 20.dp)
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
    val moves = listOf<String>("Sombero","Night Move","Dragon Move","Elcalsic","Saton","Ramboz")
    Card(elevation = 10.dp, shape = RectangleShape, modifier = Modifier.padding(8.dp)) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth(), verticalAlignment = CenterVertically) {
                Text(text = "Moves", fontSize = 14.sp,color = Color.Black)
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
            LazyVerticalGrid( GridCells.Fixed(2),contentPadding = PaddingValues(8.dp)){
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
