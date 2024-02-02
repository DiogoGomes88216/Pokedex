package com.example.pokedex.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RemoteKeys (
    @PrimaryKey val name: String,
    val prevKey: Int?,
    val nextKey: Int?
)
