package com.example.pokedex.domain.mappers

import com.example.pokedex.data.local.PokemonInfoEntity
import com.example.pokedex.data.network.models.PokemonInfoDto
import com.example.pokedex.data.network.models.Stat
import com.example.pokedex.data.network.models.Type
import com.example.pokedex.domain.models.PokemonInfo

object PokemonInfoMapper {

    fun PokemonInfoDto.toPokemonInfo(imageUrl: String) = PokemonInfo(
        id = id,
        name = name,
        height = height,
        weight = weight,
        imageUrl = imageUrl,
        //types = types,
        //stats = stats,
    )

    fun PokemonInfoEntity.toPokemonInfo() = PokemonInfo(
        id = id,
        name = name,
        height = height,
        weight = weight,
        imageUrl = imageUrl,
        //types = types,
        //stats = stats,
    )

    fun PokemonInfoDto.toPokemonInfoEntity(imageUrl: String) = PokemonInfoEntity(
        id = id,
        name = name,
        height = height,
        weight = weight,
        imageUrl = imageUrl,
        //types = types,
        //stats = stats,
    )

}