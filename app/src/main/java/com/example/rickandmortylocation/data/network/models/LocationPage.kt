package com.example.rickandmortylocation.data.network.models

import kotlinx.serialization.Serializable

@Serializable
class LocationPage(
    val info: Info,
    val results: List<Location>
)
