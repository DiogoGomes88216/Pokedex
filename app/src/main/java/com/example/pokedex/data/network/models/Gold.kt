package com.example.pokedex.data.network.models


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Gold(
    @SerializedName("back_default")
    val backDefault: String,
    @SerializedName("back_shiny")
    val backShiny: String,
    @SerializedName("front_default")
    val frontDefault: String,
    @SerializedName("front_shiny")
    val frontShiny: String,
    @SerializedName("front_transparent")
    val frontTransparent: String
)