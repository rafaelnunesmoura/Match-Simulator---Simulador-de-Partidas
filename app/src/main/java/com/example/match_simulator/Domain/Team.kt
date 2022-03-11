package com.example.match_simulator.Domain

import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName("nome")
    val name: String,
    @SerializedName("forca")
    val stars:Int,
    @SerializedName("imagem")
    val Image:String
)