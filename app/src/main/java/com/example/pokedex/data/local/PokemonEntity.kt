package com.example.pokedex.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonEntity(
    @PrimaryKey val name: String,
    val imageUrl: String,
)

