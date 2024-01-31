package com.example.pokedex.data.network.models


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class StatDto(
    @SerializedName("base_stat")
    val baseStat: Int,
    @SerializedName("effort")
    val effort: Int,
    @SerializedName("stat")
    val stat: StatDetailDto
)