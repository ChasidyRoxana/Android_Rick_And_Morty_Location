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

    private val locationList: MutableList<Location> = mutableListOf()
    private var locationInfo: Info? = null
    private var currentPage: Int = 0
    private var requestLocationPageListener: RequestLocationPageListener? = null

    fun setRequestLocationLoadPageListener(requestLocationPageListener: RequestLocationPageListener) {
        this.requestLocationPageListener = requestLocationPageListener
    }

    fun loadLocations() =
        locationList

    fun requestNextLocations() {
        if (currentPage == 0 || locationInfo?.next != null) {
            val locationPageCallback = locationApiService.getLocationPage(currentPage + 1)
            callbackPageProcessing(locationPageCallback)
        } else {
            requestLocationPageListener?.onError(Exception())
        }
    }

    private fun callbackPageProcessing(callback: Call<LocationPage>) {
        callback.enqueue(object : Callback<LocationPage> {
            override fun onFailure(call: Call<LocationPage>, t: Throwable) {
                requestLocationPageListener?.onError(t as Exception)
            }

            override fun onResponse(call: Call<LocationPage>, response: Response<LocationPage>) {
                val locationPage: LocationPage? = response.body()
                locationInfo = locationPage?.info
                val locations: MutableList<Location> = locationPage?.results ?: mutableListOf()
                if (locations.isNotEmpty()) {
                    currentPage++
                }
                locationList.addAll(locations)
                val moreData: Boolean = locationList.size != locationInfo?.count
                requestLocationPageListener?.onSuccess(locations, moreData)
            }
        })
    }


}