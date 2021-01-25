package com.example.rickandmortylocation.presenter

import com.example.rickandmortylocation.interfaces.RequestLocationPageListener
import com.example.rickandmortylocation.interfaces.MainContract
import com.example.rickandmortylocation.model.network.Location
import com.example.rickandmortylocation.model.Repository
import java.lang.Exception
import java.net.UnknownHostException

class Presenter(private val view: MainContract.MainView, private val repository: Repository) :
    MainContract.MAinPresenter {

    private var loading: Boolean = false

    override fun onCreate() {
        val locations = repository.loadLocations()
        view.updateRecyclerView(locations)
        onRequestNextLocations()
    }

    override fun onRequestNextLocations() {
        if (!loading) {
            loadingState(true)
            view.setReconnectionButtonVisibility(false)
            repository.setRequestLocationLoadPageListener(getLoadPageListener())
            repository.requestNextLocations()
        }
    }

    private fun getLoadPageListener(): RequestLocationPageListener =
        object : RequestLocationPageListener {
            override fun onError(e: Exception) {
                loadingState(false)
                if (e is UnknownHostException) {
                    view.setReconnectionButtonVisibility(true)
                }
            }

            override fun onSuccess(locationList: List<Location>, moreData: Boolean) {
                view.updateRecyclerView(locationList)
                loadingState(false)
                if (!moreData) {
                    view.clearScrollListeners()
                }
            }
        }

    private fun loadingState(isLoading: Boolean) {
        view.setProgressBarVisibility(isLoading)
        loading = isLoading
    }
}