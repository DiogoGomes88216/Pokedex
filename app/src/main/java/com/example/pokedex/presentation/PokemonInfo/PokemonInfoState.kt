package com.example.pokedex.presentation.PokemonInfo

import androidx.compose.ui.graphics.Color
import com.example.pokedex.domain.models.PokemonInfo


data class PokemonInfoState(
    val isLoading: Boolean = true,
    val info: PokemonInfo = PokemonInfo(
        id = 0,
        name = "",
        height = 0,
        weight = 0,
        imageUrl = "",
        stats = emptyList(),
        types = emptyList(),
    ),
    val dominantColor: Color = Color.Black,
    val hasError: Boolean = false
)
