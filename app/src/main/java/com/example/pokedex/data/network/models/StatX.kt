package com.example.pokedex.data.network.models


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class StatX(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)