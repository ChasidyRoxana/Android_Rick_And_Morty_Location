package com.example.rickandmortylocation.data.network.models

import kotlinx.serialization.Serializable

@Serializable
class Info(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)