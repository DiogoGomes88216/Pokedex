package com.example.pokedex.domain.mappers

import com.example.pokedex.data.local.PokemonInfoEntity
import com.example.pokedex.data.local.PokemonInfoWithTypesAndStats
import com.example.pokedex.data.local.StatEntity
import com.example.pokedex.data.local.TypeEntity
import com.example.pokedex.data.network.models.PokemonInfoDto
import com.example.pokedex.data.network.models.StatDto
import com.example.pokedex.data.network.models.TypeDto
import com.example.pokedex.domain.models.PokemonInfo
import com.example.pokedex.domain.models.Stat
import com.example.pokedex.domain.models.Type

object PokemonInfoMapper {

    fun PokemonInfoDto.toPokemonInfo(imageUrl: String) = PokemonInfo(
        id = id,
        name = name,
        height = height,
        weight = weight,
        imageUrl = imageUrl,
        types = types.map { it.toType() },
        stats = stats.map { it.toStat() },
    )
    fun PokemonInfoWithTypesAndStats.toPokemonInfo() = PokemonInfo(
        id = basicInfo.id,
        name = basicInfo.name,
        height = basicInfo.height,
        weight = basicInfo.weight,
        imageUrl = basicInfo.imageUrl,
        types = types.map { it.toType() },
        stats = stats.map { it.toStat() },
    )

    fun PokemonInfoDto.toPokemonInfoEntity(imageUrl: String) = PokemonInfoEntity (
        id = id,
        name = name,
        height = height,
        weight = weight,
        imageUrl = imageUrl,
    )

    private fun StatDto.toStat() = Stat(
        baseStat = baseStat,
        effort = effort,
        name = stat.name,
        //url = stat.url,
    )

    private fun TypeDto.toType() = Type(
        slot = slot,
        name = type.name,
        //url = type.url
    )

    fun StatDto.toStatEntity(pokemonId: Int) = StatEntity(
        baseStat = baseStat,
        effort = effort,
        name = stat.name,
        pokemonId = pokemonId
        //url = stat.url,
    )

    fun TypeDto.toTypeEntity(pokemonId: Int) = TypeEntity(
        slot = slot,
        name = type.name,
        pokemonId = pokemonId
        //url = type.url
    )

    private fun StatEntity.toStat() = Stat(
        baseStat = baseStat,
        effort = effort,
        name = name,
        //url = stat.url,
    )

    private fun TypeEntity.toType() = Type(
        slot = slot,
        name = name,
        //url = type.url
    )
}