package com.example.rickandmortylocation.model.network

import com.example.rickandmortylocation.model.network.Info
import com.example.rickandmortylocation.model.network.Location
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class LocationPage(

    @SerialName("info")
    val info: Info,

    @SerialName("results")
    val results: MutableList<Location>
)
