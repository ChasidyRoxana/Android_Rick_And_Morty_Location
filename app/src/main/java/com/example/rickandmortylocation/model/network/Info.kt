package com.example.rickandmortylocation.model.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Info(

    @SerialName("count")
    val count: Int,

    @SerialName("pages")
    val pages: Int,

    @SerialName("next")
    val next: String?,

    @SerialName("prev")
    val prev: String?
)