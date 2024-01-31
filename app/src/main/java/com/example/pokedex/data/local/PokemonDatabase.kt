package com.example.pokedex.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        PokemonEntity::class,
        RemoteKeys::class,
        PokemonInfoEntity::class,
        StatEntity::class,
        TypeEntity::class,
   ],
    version = 1
)
abstract class PokemonDatabase: RoomDatabase() {
    abstract val dao: PokemonDao
    abstract fun remoteKeysDao(): RemoteKeysDao
}