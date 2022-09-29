package com.carlos.pokemen.utils

import androidx.compose.ui.graphics.Color
import com.carlos.pokemen.ui.theme.*

object PokemonUtils {


    fun getAbbreviation(ability: String): String {
        return when (ability) {
            "hp" -> "HP"
            "attack" -> "ATK"
            "defense" -> "DEF"
            "special-attack" -> "SATK"
            "special-defense" -> "SDEF"
            "speed" -> "SPD"
            else -> "Unknown"
        }
    }


    fun getColor(ability: String): Color{
        return when (ability) {
            "hp" -> Red700
            "attack" -> gold
            "defense" -> blue
            "special-attack" -> navajoWhite
            "special-defense" -> lightBlue
            "speed" -> water
            else -> Red900
        }
    }

}