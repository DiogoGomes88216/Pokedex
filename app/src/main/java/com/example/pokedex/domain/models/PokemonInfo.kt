package com.example.pokedex.domain.models

import com.example.pokedex.data.network.models.Stat
import com.example.pokedex.data.network.models.Type


data class PokemonInfo(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val height: Int,
    val weight: Int,
    //val types: List<Type>,
    //val stats: List<Stat>,
)
