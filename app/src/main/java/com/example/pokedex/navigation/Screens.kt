package com.example.diogopokemon.navigation

internal sealed class Screens(val route: String) {
    object PokemonList: Screens("pokemon_list")
    object PokemonInfo: Screens("pokemon_info/{${NAME_KEY}}") {
        fun createRoute(name: String) = "pokemon_info/$name"
    }

    companion object {
        const val NAME_KEY = "name"
    }
}