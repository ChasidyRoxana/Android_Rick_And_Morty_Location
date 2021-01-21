package com.example.rickandmortylocation.network

import com.example.rickandmortylocation.model.Location
import com.example.rickandmortylocation.model.LocationPage
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface LocationApiService {
//    @GET("location/")
//    fun getLocation(): Call<LocationPage>
//
//    @GET("location/")
//    fun getLocationByName(
//        @Query("name") locationName: String
//    ): Call<LocationPage>

    @GET("location/")
    fun getPageLocation( //fixme fun name
        @Query("page") page: Int
    ): Call<LocationPage>

//    @GET("location/")
//    fun getLocation(
//        @QueryMap option: Map<String, String>
//    ): Call<List<Location>>
}