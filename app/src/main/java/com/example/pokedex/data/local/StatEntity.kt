package com.example.pokedex.data.local

import androidx.room.Entity

@Entity(primaryKeys= [ "name", "pokemonId" ] )
data class StatEntity (
    val baseStat: Int,
    val effort: Int,
    val name: String,
    val pokemonId: Int
    //val url: String
)