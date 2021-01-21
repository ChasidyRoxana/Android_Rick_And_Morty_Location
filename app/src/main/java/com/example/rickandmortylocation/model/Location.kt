package com.example.rickandmortylocation.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Location(

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