package com.example.rickandmortylocation.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationPage(

    @SerialName("info")
    val info: Info,

    @SerialName("results")
    val results: List<Location>
)
