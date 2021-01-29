package com.example.rickandmortylocation.data.network

import com.example.rickandmortylocation.data.network.models.LocationPage
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationApiService {

    @GET("location")
    fun getLocationPage(
        @Query("page") page: Int
    ): Call<LocationPage>
}