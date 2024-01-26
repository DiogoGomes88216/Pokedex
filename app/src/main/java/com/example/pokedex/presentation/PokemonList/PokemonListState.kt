package com.example.pokedex.presentation.PokemonList

import com.example.pokedex.domain.models.Pokemon

data class PokemonListState(
    val isLoading: Boolean = true,
    val results: List<Pokemon> = emptyList(),
    val hasError: Boolean = false
)
