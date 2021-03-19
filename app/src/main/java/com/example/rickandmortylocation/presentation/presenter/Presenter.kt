package com.example.rickandmortylocation.presentation.presenter

import com.example.rickandmortylocation.presentation.interfaces.RequestLocationPageListener
import com.example.rickandmortylocation.presentation.interfaces.MainContract
import com.example.rickandmortylocation.data.repository.Repository
import com.example.rickandmortylocation.domain.models.Location
import java.lang.Exception

class Presenter(private val view: MainContract.MainView, private val repository: Repository) :
    MainContract.MainPresenter {

    private val requestLocationPageListener = createRequestLocationPageListener()
//    private val isAllDataLoaded: Boolean
//        get() = repository.isAllDataLoaded
    private val isLoading: Boolean
        get() = repository.isLoading
    private val isReconnection: Boolean
        get() = repository.isReconnection

    override fun onCreate() {
        view.addItems(repository.getLoadedLocations())
        view.setProgressBarVisibility(isLoading)
        view.setReconnectionButtonVisibility(isReconnection)
        repository.addRequestLocationPageListener(requestLocationPageListener)
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
        view.showToast(location.name, location.countResidents)
    }

    override fun onDestroyView() {
        repository.removeRequestLocationPageListener(requestLocationPageListener)
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
        view.addItems(locations)
//        if (isAllDataLoaded) {
//            view.clearOnScrollListeners()
//        }
    }

    private fun onErrorProcessing(e: Exception) {
        view.setProgressBarVisibility(isLoading)
        view.setReconnectionButtonVisibility(isReconnection)
    }
}