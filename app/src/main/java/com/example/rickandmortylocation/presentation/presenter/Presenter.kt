package com.example.rickandmortylocation.presentation.presenter

import com.example.rickandmortylocation.R
import com.example.rickandmortylocation.presentation.interfaces.RequestLocationPageListener
import com.example.rickandmortylocation.presentation.interfaces.MainContract
import com.example.rickandmortylocation.data.network.models.Location
import com.example.rickandmortylocation.data.repository.Repository
import java.lang.Exception
import java.net.UnknownHostException

class Presenter(private val view: MainContract.MainView, private val repository: Repository) :
    MainContract.MainPresenter {

    private val requestLocationPageListener = createRequestLocationPageListener()
    private val isAllDataLoaded: Boolean
        get() = repository.isAllDataLoaded
    private val isLoading: Boolean
        get() = repository.isLoading
    private val isReconnection: Boolean
        get() = repository.isReconnection

    override fun onCreate() {
        view.setupAdapter()
        view.addItemsToRecyclerView(repository.getLoadedLocations())
        view.setProgressBarVisibility(isLoading)
        view.setReconnectionButtonVisibility(isReconnection)
        repository.setRequestLocationPageListener(requestLocationPageListener)
        if (!isAllDataLoaded) {
            view.addOnScrollListener()
        }
        if (repository.isFirstLaunch) {
            requestNextLocationsAndShowProgressBar()
        }
    }

    override fun onScrolledToLastElement() {
        if (!isReconnection) {
            requestNextLocationsAndShowProgressBar()
        }
    }

    override fun onReconnectionButtonClicked() {
        requestNextLocationsAndShowProgressBar()
    }

    override fun onItemClicked(location: Location) {
        val pluralsId = R.plurals.residents_in_location
        val countResidents: Int = location.residents?.size ?: 0
        view.showToast(pluralsId, countResidents, location.name)
    }

    override fun onDestroyView() {
        repository.clearRequestLocationPageListener(requestLocationPageListener)
    }

    private fun requestNextLocationsAndShowProgressBar() {
        if (!isLoading) {
            view.setProgressBarVisibility(true)
            repository.requestNextLocations()
        }
    }

    private fun createRequestLocationPageListener(): RequestLocationPageListener =
        object : RequestLocationPageListener {
            override fun onError(e: Exception) {
                onErrorProcessing(e)
            }

            override fun onSuccess(locations: List<Location>) {
                onSuccessProcessing(locations)
            }
        }

    private fun onSuccessProcessing(locations: List<Location>) {
        view.setProgressBarVisibility(isLoading)
        view.setReconnectionButtonVisibility(isReconnection)
        view.addItemsToRecyclerView(locations)
        if (isAllDataLoaded) {
            view.clearOnScrollListeners()
        }
    }

    private fun onErrorProcessing(e: Exception) {
        view.setProgressBarVisibility(isLoading)
        if (e is UnknownHostException) {
            view.setReconnectionButtonVisibility(isReconnection)
        }
    }
}