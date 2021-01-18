package com.example.rickandmortylocation.presenter

import com.example.rickandmortylocation.model.LocationPage
import com.example.rickandmortylocation.network.NetworkService
import com.example.rickandmortylocation.view.MainFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Presenter(private val view: MainFragment, networkService: NetworkService) {

    private val locationApiService = networkService.createLocationApiService()

    fun onCreate() {
        val locationCallback = locationApiService.getPageLocation(1)
        locationCallback.enqueue(object : Callback<LocationPage> {
            override fun onFailure(call: Call<LocationPage>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<LocationPage>, response: Response<LocationPage>) {
                val locationPage: LocationPage? = response.body()
                view.setRecyclerView(locationPage?.results ?: emptyList())
            }
        })
    }
}