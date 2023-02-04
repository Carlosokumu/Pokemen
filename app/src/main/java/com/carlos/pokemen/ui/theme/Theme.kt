package com.carlos.pokemen.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val DarkColorPalette = darkColors(
    primary = Red700,
    primaryVariant = Purple200,
    secondaryVariant = Purple200,
    secondary = Teal200,
    onBackground = Color.Black,
    surface = Color.Black,
    onSurface = Color.Black,

)



private val LightColorPalette = lightColors(
    primaryVariant = Purple200,
    background = Whitemain,
    surface = Whitemain ,
    primary =  colorPrimary,
    secondary = Whitemain,
    secondaryVariant = Red900,
    error = Red800



)

@Composable
fun PokedexTheme(darkTheme: Boolean, content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}