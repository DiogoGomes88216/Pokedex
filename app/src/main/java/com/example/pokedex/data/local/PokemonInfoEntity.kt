package com.example.pokedex.data.local

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
data class PokemonInfoEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val imageUrl: String,
    val height: Int,
    val weight: Int,
)

data class PokemonInfoWithTypesAndStats(

    @Embedded val basicInfo: PokemonInfoEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "pokemonId"
    )
    val types: List<TypeEntity>,

    @Relation(
        parentColumn = "id",
        entityColumn = "pokemonId"
    )
    val stats: List<StatEntity>,
)
