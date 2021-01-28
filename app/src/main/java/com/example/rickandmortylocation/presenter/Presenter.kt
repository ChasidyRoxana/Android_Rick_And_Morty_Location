package com.example.rickandmortylocation.presenter

import com.example.rickandmortylocation.interfaces.RequestLocationPageListener
import com.example.rickandmortylocation.interfaces.MainContract
import com.example.rickandmortylocation.model.network.Location
import com.example.rickandmortylocation.model.Repository
import java.lang.Exception
import java.net.UnknownHostException

class Presenter(private val view: MainContract.MainView, private val repository: Repository) :
    MainContract.MAinPresenter {

    override fun onCreate() {
        view.setupAdapter()
        view.addItemsToRecyclerView(repository.loadLocations())
        view.setProgressBarVisibility(repository.isLoading)
        view.setReconnectionButtonVisibility(repository.isReconnection)
        repository.setRequestLocationPageListener(createRequestLocationPageListener())
        if (!repository.isAllDataLoaded) {
            view.addOnScrollListener()
        }
        if (repository.isFirstLaunch) {
            requestNextLocations()
        }
    }

    override fun onScrolledToLastElement() {
        if (!repository.isReconnection) {
            requestNextLocations()
        }
    }

    override fun onReconnectionButtonClicked() {
        requestNextLocations()
    }

    private fun requestNextLocations() {
        if (!repository.isLoading) {
            view.setProgressBarVisibility(true)
            repository.requestNextLocations()
        }
    }

    private fun createRequestLocationPageListener(): RequestLocationPageListener =
        object : RequestLocationPageListener {
            override fun onError(e: Exception) {
                view.setProgressBarVisibility(repository.isLoading)
                if (e is UnknownHostException) {
                    view.setReconnectionButtonVisibility(repository.isReconnection)
                }
            }

            override fun onSuccess(locationList: List<Location>) {
                view.setProgressBarVisibility(repository.isLoading)
                view.setReconnectionButtonVisibility(repository.isReconnection)
                view.addItemsToRecyclerView(locationList)
                if (repository.isAllDataLoaded) {
                    view.clearOnScrollListeners()
                }
            }
        }
}