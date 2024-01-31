package com.example.pokedex.data.local

import androidx.room.Entity

@Entity(primaryKeys= [ "name", "pokemonId" ] )
data class TypeEntity(
    val slot: Int,
    val name: String,
    val pokemonId: Int,
    //val url: String
)
