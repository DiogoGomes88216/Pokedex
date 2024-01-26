package com.example.pokedex.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonInfoEntity(
        pokemonInfoEntity: PokemonInfoEntity
    )

    @Query("DELETE FROM pokemoninfoentity")
    suspend fun clearPokemonInfoEntity()

    @Query(
        """
            SELECT * 
            FROM pokemoninfoentity
            WHERE name LIKE :query 
        """
    )
    suspend fun getPokemonInfo(query: String): PokemonInfoEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonEntity(pokemons: List<PokemonEntity>)

    @Query("DELETE FROM pokemonentity")
    suspend fun clearPokemonEntity()

    @Query(
        """
            SELECT *
            FROM pokemonentity
        """
    )
    fun pagingSource(): PagingSource<Int, PokemonEntity>

}