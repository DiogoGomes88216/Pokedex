package com.example.pokedex.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonInfoEntity(
        pokemonInfoEntity: PokemonInfoEntity
    )

    @Query("DELETE FROM pokemoninfoentity")
    suspend fun clearPokemonInfoEntity()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStatEntity(
        statEntity: List<StatEntity>
    )

    @Query("DELETE FROM statentity")
    suspend fun clearStatEntity()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTypeEntity(
        typeEntity: List<TypeEntity>
    )

    @Query("DELETE FROM typeentity")
    suspend fun clearTypeEntity()


    suspend fun insertPokemonInfoWithTypesAndStats(
        basicInfo: PokemonInfoEntity,
        stats: List<StatEntity>,
        types: List<TypeEntity>
    ) {
        insertPokemonInfoEntity(basicInfo)
        insertTypeEntity(types)
        insertStatEntity(stats)
    }

    @Transaction
    @Query("SELECT * FROM pokemoninfoentity")
    fun getPokemonInfoWithTypesAndStats(): List<PokemonInfoWithTypesAndStats>

    @Transaction
    @Query(
        """
            SELECT * 
            FROM pokemoninfoentity
            WHERE name LIKE :name
        """
    )
   suspend fun getPokemonInfoWithTypesAndStatsByName(name: String): PokemonInfoWithTypesAndStats?

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