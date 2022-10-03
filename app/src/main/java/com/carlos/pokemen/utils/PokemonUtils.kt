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

    fun getProgress(stat: Int): Float{
        return (stat/100).toFloat()
    }

    fun getTypeColor(type: String): Color {
        return when (type) {
            "fighting" -> fighting
            "flying" -> flying
            "poison" -> poison
            "ground" -> ground
            "rock" -> rock
            "bug" -> bug
            "ghost" -> ghost
            "steel" -> steel
            "fire" -> fire
            "water" -> water
            "grass" -> grass
            "electric" -> electric
            "psychic" -> psychic
            "ice" ->  ice
            "dragon" -> dragon
            "fairy" -> fairy
            "dark" -> Color.DarkGray
            else -> Color.LightGray
        }
    }

    fun getImageUrl(url: String): String {
        val index = url.split("/".toRegex()).dropLast(1).last()
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$index.png"
    }


    fun getIndex(index: Int): String{
        return when(index){
            in 0..9 -> "#00".plus(index.toString())
            in 10..99 -> "#0".plus(index.toString())
            else -> "#".plus(index.toString())
        }
    }

}