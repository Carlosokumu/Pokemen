package com.carlos.pokemen.screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.carlos.pokemen.ui.theme.mainColor
import com.ramcosta.composedestinations.annotation.Destination
import com.stephenvinouze.segmentedprogressbar.SegmentedProgressBar
import com.stephenvinouze.segmentedprogressbar.models.SegmentColor
import com.stephenvinouze.segmentedprogressbar.models.SegmentCoordinates


@Composable
fun DetailsScreen(){

}

@Preview
@Composable
fun PokemonInfo(){
    Card() {
        Column() {
            Text(text = "001")
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = "Kakuna")
                Spacer(modifier = Modifier.size(10.dp))
                Text(text = "Poison", style = typography.body1.merge(), modifier = Modifier
                    .clip(
                        shape = RoundedCornerShape(
                            size = 5.dp,
                        ),
                    )

                    .background(mainColor))
                Spacer(modifier = Modifier.size(10.dp))


            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.wrapContentHeight()) {
                    Text(text = "Hp")
                    SegmentedProgressBar(
                        segmentCount = 3,
                        modifier = Modifier,
                        spacing = 10.dp,
                        angle = 30f, // Can also be negative to invert the bevel side
                        progress = 1F,
                        segmentColor = SegmentColor(
                            color = Color.Gray,
                            alpha = 0.3f,
                        ),
                        progressColor = SegmentColor(
                            color = Color.Green,
                            alpha = 1f,
                        ),
                        drawSegmentsBehindProgress = false, // See Javadoc for more explanation on this parameter
                        progressAnimationSpec = tween( // You can give any animation spec you'd like
                            durationMillis = 1000,
                            easing = LinearEasing,
                        ),
                        onProgressChanged = { progress: Float, progressCoordinates: SegmentCoordinates ->
                            // Get notified at each recomposition cycle when a progression occurs.
                            // You can use the current progression or the coordinates the progress segment currently has.
                        },
                        onProgressFinished = {
                            // Get notified when the progression animation ends.
                        }
                    )
                }
            }
        }
    }
}