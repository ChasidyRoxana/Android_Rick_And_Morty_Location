package com.example.rickandmortylocation.data.models

import kotlinx.serialization.Serializable

@Serializable
class PageApi(
    val info: PageInfoApi,
    val results: List<LocationApi>
)
