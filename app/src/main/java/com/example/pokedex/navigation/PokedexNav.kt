package com.example.pokedex.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.diogopokemon.navigation.Screens
import com.example.pokedex.presentation.PokemonInfo.PokemonInfoScreen
import com.example.pokedex.presentation.PokemonList.PokemonListScreen

@Composable
fun PokedexNav() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Screens.PokemonList.route) {
        composable(route = Screens.PokemonList.route) {
            PokemonListScreen(
                onNavigateToDetails = { name, color ->
                    navController.navigate(Screens.PokemonInfo.createRoute(name = name, color = color))
                }
            )
        }

        composable(
            route = Screens.PokemonInfo.route,
            arguments = listOf(
                navArgument(Screens.NAME_KEY) {
                    type = NavType.StringType
                }
            )
        ) {
            PokemonInfoScreen(
                onNavigateBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}