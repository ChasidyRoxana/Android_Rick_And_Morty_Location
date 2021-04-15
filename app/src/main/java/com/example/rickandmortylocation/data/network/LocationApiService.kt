package com.example.rickandmortylocation.data.network

import com.example.rickandmortylocation.data.model.ResidentApi
import com.example.rickandmortylocation.data.model.LocationApi
import com.example.rickandmortylocation.data.model.PageApi
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LocationApiService {

    @GET("location")
    suspend fun getLocationPage(
        @Query("page") page: Int
    ): PageApi

    @GET("location/{id}")
    suspend fun getLocationById(
        @Path("id") locationId: Int
    ): LocationApi

    @GET("character/{id}")
    suspend fun getCharacterById(
        @Path("id") characterId: String
    ): List<ResidentApi>
}