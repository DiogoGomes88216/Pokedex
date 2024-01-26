package com.example.pokedex.di

import com.example.pokedex.data.local.PokemonDatabase
import com.example.pokedex.data.network.PokemonApi
import com.example.pokedex.data.repositories.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepoModule {

    @Provides
    @Singleton
    fun providesPokemonRepository(
        api: PokemonApi,
        db: PokemonDatabase
    ): PokemonRepository {
        return PokemonRepository(
            api = api,
            db = db,
        )
    }
}
