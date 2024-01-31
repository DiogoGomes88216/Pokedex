package com.example.pokedex.data.network.models


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class TypeDto(
    @SerializedName("slot")
    val slot: Int,
    @SerializedName("type")
    val type: TypeDetailDto
)