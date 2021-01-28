package com.example.rickandmortylocation.model.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationApiService {

    @GET("location")
    fun getLocationPage(
        @Query("page") page: Int
    ): Call<LocationPage>
}