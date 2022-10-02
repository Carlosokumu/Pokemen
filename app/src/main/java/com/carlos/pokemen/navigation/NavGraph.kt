package com.carlos.pokemen.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.carlos.pokemen.screens.DetailsScreen
import com.carlos.pokemen.screens.Home
import okhttp3.internal.threadName

@ExperimentalMaterialApi
@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val actions = remember(navController) { MainActions(navController) }

    NavHost(navController, startDestination = Screen.Home.route) {

        //Home Screen
        composable(Screen.Home.route) {
           Home(mainActions = actions)
        }

        //Details Screen
        composable(Screen.DetailsScreen.route  + "/{name}" + "/{index}"  + "/{imageUrl}" , arguments = listOf(
            navArgument("name") { type = NavType.StringType },navArgument("index") { type = NavType.IntType },navArgument("imageUrl") { type = NavType.StringType }
        )) {
             DetailsScreen(detailsViewModel = hiltViewModel(),it.arguments?.getString("name"),it.arguments?.getInt("index"),it.arguments?.getString("imageUrl"))
        }


    }
}

class MainActions(private val navController: NavHostController) {


    val gotoDetailsScreen: (name: String,index: Int,imageUrl: String) -> Unit = { name,index,imageUrl ->
        navController.navigate(route =Screen.DetailsScreen.route + "/${name}" + "/$index" +  "/$imageUrl")
    }
}