package com.example.pokedex.domain.mappers

import com.example.pokedex.data.local.PokemonEntity
import com.example.pokedex.data.network.models.PokemonDto
import com.example.pokedex.domain.models.Pokemon

object PokemonMapper {

    fun PokemonDto.toPokemon(imageUrl: String) = Pokemon(
        name = name,
        imageUrl = imageUrl
    )

    fun PokemonDto.toPokemonEntity(imageUrl: String) = PokemonEntity(
        name = name,
        imageUrl = imageUrl
    )

    fun PokemonEntity.toPokemon() = Pokemon(
        name = name,
        imageUrl = imageUrl
    )
}