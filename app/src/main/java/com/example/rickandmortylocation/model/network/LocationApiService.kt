package com.example.rickandmortylocation.model.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationApiService {
//    @GET("location/")
//    fun getLocation(): Call<LocationPage>
//
//    @GET("location/")
//    fun getLocationByName(
//        @Query("name") locationName: String
//    ): Call<LocationPage>

    @GET("location/")
    fun getLocationPage( //fixme fun name
        @Query("page") page: Int
    ): Call<LocationPage>

//    @GET("location/")
//    fun getLocation(
//        @QueryMap option: Map<String, String>
//    ): Call<List<Location>>
}