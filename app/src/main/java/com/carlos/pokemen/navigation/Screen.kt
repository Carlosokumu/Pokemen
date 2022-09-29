package com.carlos.pokemen.navigation

import androidx.annotation.StringRes
import com.example.pokedex.R

sealed class Screen(val route: String, @StringRes val resourceId: Int) {


    object  Home: Screen(route = "Home", R.string.home)
    object  DetailsScreen: Screen(route = "Details",R.string.details)

}