package com.example.pokedex.data.repositories


import androidx.paging.PagingSource
import com.example.pokedex.data.local.PokemonDatabase
import com.example.pokedex.data.local.PokemonEntity
import com.example.pokedex.data.network.PokemonApi
import com.example.pokedex.domain.mappers.PokemonInfoMapper.toPokemonInfo
import com.example.pokedex.domain.mappers.PokemonInfoMapper.toPokemonInfoEntity
import com.example.pokedex.domain.mappers.PokemonMapper.toPokemon
import com.example.pokedex.domain.models.Pokemon
import com.example.pokedex.domain.models.PokemonInfo
import javax.inject.Inject


class PokemonRepository @Inject constructor(
    private val api: PokemonApi,
    private val db: PokemonDatabase,
) {
    private val dao = db.dao

    fun getPagingSource(): PagingSource<Int, PokemonEntity> {
        return db.dao.pagingSource()
    }

    suspend fun getPokemonList(limit: Int, offset: Int): Result<List<Pokemon>> {
        return try {
            val results = api.getPokemonList(limit = limit, offset = offset)
            val domainData = results.results.map {dto ->
                val number = if(dto.url.endsWith("/")) {
                    dto.url.dropLast(1).takeLastWhile { it.isDigit() }
                } else {
                    dto.url.takeLastWhile { it.isDigit() }
                }
                dto.toPokemon(imageUrl = getImageUrl(number))
            }
            Result.success(domainData)
        } catch (ex: Exception){
            Result.failure(ex)
        }
    }

    suspend fun getPokemonInfoByName(name: String): Result<PokemonInfo> {

         dao.getPokemonInfo(name)?.let {
            return Result.success(it.toPokemonInfo())
        }

        return try {
            val response = api.getPokemonInfoByName(name = name)
            val imageUrl = getImageUrl(response.id.toString())

            dao.insertPokemonInfoEntity(
                response.toPokemonInfoEntity(imageUrl = imageUrl)
            )
            Result.success(response.toPokemonInfo(imageUrl = imageUrl))
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }

    private fun getImageUrl(id: String): String {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
    }
}