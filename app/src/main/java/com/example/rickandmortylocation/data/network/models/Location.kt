package com.example.rickandmortylocation.data.network.models

import kotlinx.serialization.Serializable

@Serializable
class Location(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>?
)