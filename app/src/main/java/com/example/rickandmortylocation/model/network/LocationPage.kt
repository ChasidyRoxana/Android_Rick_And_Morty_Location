package com.example.rickandmortylocation.model.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class LocationPage(

    @SerialName("info")
    val info: Info,

    @SerialName("results")
    val results: MutableList<Location>
)
