package com.example.rickandmortylocation.data.models

import kotlinx.serialization.Serializable

@Serializable
class PageInfoApi(
    val count: Int,
//    val pages: Int,
    val next: String?,
//    val prev: String?
)