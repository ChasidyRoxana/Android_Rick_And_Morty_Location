package com.example.rickandmortylocation.model.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Location(

    @SerialName("id")
    val id: Int,

    @SerialName("name")
    val name: String,

    @SerialName("type")
    val type: String,

    @SerialName("dimension")
    val dimension: String,

    @SerialName("residents")
    val residents: List<String>?,

    @SerialName("url")
    val url: String,
)