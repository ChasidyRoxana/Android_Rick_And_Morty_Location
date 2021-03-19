package com.example.rickandmortylocation.data.repository

import com.example.rickandmortylocation.data.mapper.LocationMapper
import com.example.rickandmortylocation.data.network.*
import com.example.rickandmortylocation.data.models.PageApi
import com.example.rickandmortylocation.domain.models.Location
import com.example.rickandmortylocation.domain.models.ResponseInfo
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
    var isReconnection: Boolean = false // hasError обозначение ошибки а не процесса переподключения
        private set
    var isAllDataLoaded: Boolean = false
        private set

    private val loadedLocations: MutableList<Location> = mutableListOf()
    private val mapper: LocationMapper = LocationMapper()
    private var currentPage: Int = 0
    private val requestLocationPageListeners: MutableList<RequestLocationPageListener> =
        mutableListOf()

    fun addRequestLocationPageListener(requestLocationPageListener: RequestLocationPageListener) {
        requestLocationPageListeners.add(requestLocationPageListener)
    }

    fun removeRequestLocationPageListener(requestLocationPageListener: RequestLocationPageListener) {
        requestLocationPageListeners.remove(requestLocationPageListener)
    }

    fun getLoadedLocations(): List<Location> =
        loadedLocations

    fun requestNextLocations() {
        if (!isLoading) {// && (currentPage == 0 || locationPageInfo?.next != null)) {
            isFirstLaunch = false
            isLoading = true
            val locationPageCallback = locationApiService.getLocationPage(currentPage + 1)
            callbackPageProcessing(locationPageCallback)
        } else {
            requestLocationPageListeners.forEach { it.onError(Exception()) }
        }
    }

    private fun callbackPageProcessing(callback: Call<PageApi>) {
        callback.enqueue(object : Callback<PageApi> {
            override fun onResponse(call: Call<PageApi>, response: Response<PageApi>) {
                val pageApi: PageApi? = response.body()
                if (pageApi != null) {
                    val responseInfo = mappingPageResponseAndCached(pageApi)
                    onSuccess(responseInfo)
                } else {
                    onError(Exception("Empty response body"))
                }
            }

            override fun onFailure(call: Call<PageApi>, t: Throwable) {
                onError(t as Exception) // fixme может быть краш
            }
        })
    }

    private fun mappingPageResponseAndCached(pageApi: PageApi): ResponseInfo {
        val responseInfo = mapper.mapPageApiToResponseInfo(pageApi)
        loadedLocations.addAll(responseInfo.locations)
        return responseInfo
    }

    private fun onSuccess(responseInfo: ResponseInfo) {
        isAllDataLoaded = loadedLocations.size == responseInfo.countLocations
        isLoading = false
        isReconnection = false
        currentPage++
        requestLocationPageListeners.forEach { it.onSuccess(responseInfo.locations) }
    }

    private fun onError(e: Exception) {
        isLoading = false
        isReconnection = true
        requestLocationPageListeners.forEach { it.onError(e) }
    }

}