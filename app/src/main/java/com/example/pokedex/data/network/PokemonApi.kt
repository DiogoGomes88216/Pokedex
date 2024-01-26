package com.example.pokedex.data.network

import com.example.pokedex.data.network.models.PokemonInfoDto
import com.example.pokedex.data.network.models.Results
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {

    @GET("v2/pokemon/")
    suspend fun getPokemonList(@Query("limit")limit: Int, @Query("offset") offset: Int): Results

    @GET("v2/pokemon/{name}")
    suspend fun getPokemonInfoByName(@Path("name")name: String): PokemonInfoDto
}
