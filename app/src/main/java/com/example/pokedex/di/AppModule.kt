package com.example.pokedex.di

import android.app.Application
import androidx.room.Room
import com.example.pokedex.data.local.PokemonDatabase
import com.example.pokedex.data.network.PokemonApi
import com.example.pokedex.data.network.PokemonRemoteMediator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesPokemonApi(): PokemonApi {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(PokemonApi::class.java)
    }

    @Provides
    @Singleton
    fun providesPokemonDatabase(app: Application): PokemonDatabase {
        return Room.databaseBuilder(
            app,
            PokemonDatabase::class.java,
            "pokemondb.db"
        ).build()
    }

    companion object{
        const val BASE_URL = "https://pokeapi.co/api/"
    }
}