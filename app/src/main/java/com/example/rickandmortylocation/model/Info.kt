package com.example.rickandmortylocation.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("info")
data class Info(

    @SerialName("count")
    val count: Int,

    @SerialName("pages")
    val pages: Int,

    @SerialName("next")
    val next: String?,

    @SerialName("prev")
    val prev: String?
)