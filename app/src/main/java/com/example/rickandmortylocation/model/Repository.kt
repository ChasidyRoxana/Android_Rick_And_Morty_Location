package com.example.rickandmortylocation.model

import com.example.rickandmortylocation.interfaces.RequestLocationPageListener
import com.example.rickandmortylocation.model.network.Info
import com.example.rickandmortylocation.model.network.Location
import com.example.rickandmortylocation.model.network.LocationPage
import com.example.rickandmortylocation.model.network.NetworkService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class Repository(networkService: NetworkService) {

    private val locationApiService = networkService.createLocationApiService()

    private val locations: MutableList<Location> = mutableListOf()
    private var locationInfo: Info? = null
    private var currentPage: Int = 0
    private var requestLocationPageListener: RequestLocationPageListener? = null

    var isFirstLaunch = true
        private set

    var isLoading: Boolean = false
        private set

    var isReconnection: Boolean = false
        private set

    var isAllDataLoaded: Boolean = false
        private set

    fun setRequestLocationPageListener(requestLocationPageListener: RequestLocationPageListener) {
        this.requestLocationPageListener = requestLocationPageListener
    }

    fun loadLocations() =
        locations

    fun requestNextLocations() {
        if (!isLoading && (currentPage == 0 || locationInfo?.next != null)) {
            isFirstLaunch = false
            isLoading = true
            val locationPageCallback = locationApiService.getLocationPage(currentPage + 1)
            callbackPageProcessing(locationPageCallback)
        } else {
            requestLocationPageListener?.onError(Exception())
        }

    }

    private fun callbackPageProcessing(callback: Call<LocationPage>) {
        callback.enqueue(object : Callback<LocationPage> {
            override fun onFailure(call: Call<LocationPage>, t: Throwable) {
                isLoading = false
                isReconnection = true
                requestLocationPageListener?.onError(t as Exception)
            }

            override fun onResponse(call: Call<LocationPage>, response: Response<LocationPage>) {
                val locationPage: LocationPage? = response.body()
                locationInfo = locationPage?.info
                val locationsFromResponse = locationPage?.results ?: mutableListOf()
                locations.addAll(locationsFromResponse)
                isAllDataLoaded = locations.size == locationInfo?.count
                currentPage++
                isLoading = false
                isReconnection = false
                requestLocationPageListener?.onSuccess(locationsFromResponse)
            }
        })
    }
}