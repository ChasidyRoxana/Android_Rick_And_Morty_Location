package com.example.rickandmortylocation.data.model

import kotlinx.serialization.Serializable

@Serializable
class LocationApi(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>?
)