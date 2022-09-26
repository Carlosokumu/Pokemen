package com.carlos.pokemen.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carlos.pokemen.ui.theme.mainColor
import kotlin.random.Random


@Composable
fun DetailsScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        PokemonInfo()
        Spacer(modifier = Modifier.size(10.dp))
        MoreInfo()
        Spacer(modifier = Modifier.size(10.dp))
        PokemonMoves()
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
            Text(text = "001")
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(text = "Kakuna")
                Spacer(modifier = Modifier.size(10.dp))
                Text(
                    text = "Poison", style = typography.body1.merge(), modifier = Modifier
                        .clip(
                            shape = RoundedCornerShape(
                                size = 5.dp,
                            ),
                        )

                        .background(mainColor)
                )
                Spacer(modifier = Modifier.size(10.dp))


            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(modifier = Modifier.wrapContentHeight()) {
                    Text(text = "Hp")
                    LinearProgressIndicator(progress = 0.5f, color = Color.Red)
                    Spacer(modifier = Modifier.size(5.dp))
                    Text(text = "Hp")
                    LinearProgressIndicator(progress = 0.5f)
                    Spacer(modifier = Modifier.size(5.dp))
                    Text(text = "Hp")
                    LinearProgressIndicator(progress = 0.5f)
                    Spacer(modifier = Modifier.size(5.dp))
                    Text(text = "Hp")
                    LinearProgressIndicator(progress = 0.5f)

                }
                Text(
                    text = "Hello",
                    color = Color.Black,
                    modifier = Modifier.align(CenterVertically)
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
            Text(text = "Breeding", color = Color.Black, modifier = Modifier.padding(10.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Column {
                    // Text(text = "Breeding", color = Color.Black)
                    Spacer(modifier = Modifier.size(10.dp))
                    Row(horizontalArrangement = Arrangement.SpaceBetween) {

                        Column {
                            Text(
                                text = "Height",
                                color = Color.Black,
                                modifier = Modifier.padding(10.dp)
                            )
                            Card(
                                border = BorderStroke(1.dp, Color.LightGray),
                                modifier = Modifier.padding(10.dp)
                            ) {
                                Row(horizontalArrangement = Arrangement.SpaceAround) {
                                    Text(
                                        text = "204",
                                        color = Color.Black,
                                        modifier = Modifier.padding(5.dp)
                                    )
                                    Text(
                                        text = "204",
                                        color = Color.Black,
                                        modifier = Modifier.padding(5.dp)
                                    )
                                }
                            }
//                            Row(horizontalArrangement = Arrangement.SpaceBetween) {
//                                Text(text = "123", color = Color.Black)
//                            }
                        }
                        Column {
                            Text(
                                text = "Weight",
                                color = Color.Black,
                                modifier = Modifier.padding(10.dp)
                            )
                            Card(
                                border = BorderStroke(1.dp, Color.LightGray),
                                modifier = Modifier.padding(10.dp),
                                shape = RectangleShape
                            ) {
                                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                                    Text(
                                        text = "204",
                                        color = Color.Black,
                                        modifier = Modifier.padding(5.dp)
                                    )
                                    Text(
                                        text = "204",
                                        color = Color.Black,
                                        modifier = Modifier.padding(5.dp)
                                    )
                                }
                            }
//                            Row(horizontalArrangement = Arrangement.SpaceBetween) {
//                                Text(text = "123", color = Color.Black)
//                            }
                        }

                    }

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
            Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Moves", fontSize = 14.sp, modifier = Modifier.padding(10.dp))
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
                        modifier = Modifier.padding(4.dp),
                        backgroundColor = Color(
                            red = Random.nextInt(0, 255),
                            green = Random.nextInt(0, 255),
                            blue = Random.nextInt(0, 255)
                        )
                    ) {
                        Text(
                            text = item,
                            fontSize = 42.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(24.dp)
                        )
                    }
                }
            }
        }
    }
}
