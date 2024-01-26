package com.example.pokedex.data.network.models


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class GenerationV(
    @SerializedName("black-white")
    val blackWhite: BlackWhite
)