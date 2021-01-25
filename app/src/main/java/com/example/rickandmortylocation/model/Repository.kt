package com.example.rickandmortylocation.model

import com.example.rickandmortylocation.interfaces.LoadPageListener
import com.example.rickandmortylocation.network.NetworkService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class Repository(networkService: NetworkService) {

    private val locationApiService = networkService.createLocationApiService()

    private val locationList: MutableList<Location> = mutableListOf()
    private var locationInfo: Info? = null
    private var currentPage: Int = 0
    private var loadPageListener: LoadPageListener? = null

    fun setLoadPageListener(loadPageListener: LoadPageListener) {
        this.loadPageListener = loadPageListener
    }

    fun loadLocations() = locationList

    fun getNextLocations() {
        if (currentPage == 0 || locationInfo?.next != null) {
            val locationPageCallback = locationApiService.getPageLocation(currentPage + 1)
            callbackPageProcessing(locationPageCallback)
        } else {
            loadPageListener?.onError(Exception())
        }
    }

    private fun callbackPageProcessing(callback: Call<LocationPage>) {
        callback.enqueue(object : Callback<LocationPage> {
            override fun onFailure(call: Call<LocationPage>, t: Throwable) {
                loadPageListener?.onError(t as Exception)
            }

            override fun onResponse(call: Call<LocationPage>, response: Response<LocationPage>) {
                val locationPage: LocationPage? = response.body()
                locationInfo = locationPage?.info
                val locations: MutableList<Location> = locationPage?.results ?: mutableListOf()
                if (locations.isNotEmpty()) {
                    currentPage++
                }
                val moreData: Boolean = locationList.size != locationInfo?.pages
                locationList.addAll(locations)
                loadPageListener?.onSuccess(locations, moreData)
            }
        })
    }


}