package com.example.diogopokemon.navigation

internal sealed class Screens(val route: String) {
    object PokemonList: Screens("pokemon_list")
    object PokemonInfo: Screens("pokemon_info/{${NAME_KEY}}/{${COLOR_KEY}}") {
        fun createRoute(name: String, color: Int) = "pokemon_info/$name/$color"
    }

    companion object {
        const val NAME_KEY = "name"
        const val COLOR_KEY = "color"
    }
}