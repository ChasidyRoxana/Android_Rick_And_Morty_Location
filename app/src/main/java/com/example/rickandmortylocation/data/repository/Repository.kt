package com.example.rickandmortylocation.data.repository

import com.example.rickandmortylocation.data.network.*
import com.example.rickandmortylocation.data.network.models.Info
import com.example.rickandmortylocation.data.network.models.Location
import com.example.rickandmortylocation.data.network.models.LocationPage
import com.example.rickandmortylocation.presentation.interfaces.RequestLocationPageListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class Repository(private val locationApiService: LocationApiService) {

    var isFirstLaunch = true
        private set
    var isLoading: Boolean = false
        private set
    var isReconnection: Boolean = false
        private set
    var isAllDataLoaded: Boolean = false
        private set

    private val loadedLocations: MutableList<Location> = mutableListOf()
    private var locationsFromResponse: List<Location> = listOf()
    private var locationInfo: Info? = null
    private var currentPage: Int = 0
    private var requestLocationPageListener: RequestLocationPageListener? = null

    fun setRequestLocationPageListener(requestLocationPageListener: RequestLocationPageListener) {
        this.requestLocationPageListener = requestLocationPageListener
    }

    fun getLoadedLocations() =
        loadedLocations

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
            override fun onResponse(call: Call<LocationPage>, response: Response<LocationPage>) {
                val locationPage: LocationPage? = response.body()
                mappingLocationPage(locationPage)
                onSuccess()
            }

            override fun onFailure(call: Call<LocationPage>, t: Throwable) {
                onError(t as Exception)
            }
        })
    }

    private fun mappingLocationPage(locationPage: LocationPage?) {
        locationInfo = locationPage?.info
        locationsFromResponse = locationPage?.results ?: listOf()
        loadedLocations.addAll(locationsFromResponse)
    }

    private fun onSuccess() {
        isAllDataLoaded = loadedLocations.size == locationInfo?.count
        isLoading = false
        isReconnection = false
        currentPage++
        requestLocationPageListener?.onSuccess(locationsFromResponse)
    }

    private fun onError(e: Exception) {
        isLoading = false
        isReconnection = true
        requestLocationPageListener?.onError(e)
    }

}