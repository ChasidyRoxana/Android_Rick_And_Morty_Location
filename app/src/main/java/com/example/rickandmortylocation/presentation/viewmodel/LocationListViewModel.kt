package com.example.rickandmortylocation.presentation.viewmodel

import android.util.Log
import com.example.rickandmortylocation.presentation.command.LocationListCommand
import com.example.rickandmortylocation.presentation.model.LocationItem
import com.example.rickandmortylocation.presentation.model.LocationListScreenState
import com.example.rickandmortylocation.presentation.model.RequestLocationPageListener

class LocationListViewModel : BaseViewModel<LocationListScreenState, LocationListCommand>(
    LocationListScreenState()
) {

//    private val requestLocationPageListener = createRequestLocationPageListener()

    fun init() {
        updateScreenState()
        // нет ссылки на репозиторий, надо добавлять корутины, наверное
    }

    @Synchronized
    private fun updateScreenState(
        locationItems: List<LocationItem> = model.locationItems,
        isErrorMessageVisible: Boolean = model.isErrorMessageVisible,
        shouldRefreshView: Boolean = true
    ) {
        model = LocationListScreenState(
            locationItems = locationItems,
            isErrorMessageVisible = isErrorMessageVisible
        )
        if (shouldRefreshView) {
            refreshView()
        }
    }

    fun onShowDetailClicked(location: LocationItem) {
        executeCommand(LocationListCommand.NavigateToLocationDetailScreen(location.id))
    }

    override fun onCleared() {
//        requestLocationPageListener
    }

//    private fun createRequestLocationPageListener(): RequestLocationPageListener =
//        object : RequestLocationPageListener {
//            override fun onError(t: Throwable) {
//                onErrorProcessing(t)
//            }
//
//            override fun onSuccess(locations: List<LocationItem>) {
//                onSuccessProcessing(locations)
//            }
//        }
//
//    private fun onSuccessProcessing(locations: List<LocationItem>) {
//        updateScreenState(locationItems = locations, isErrorMessageVisible = false)
//    }
//
//    private fun onErrorProcessing(t: Throwable) {
//        updateScreenState(isErrorMessageVisible = true)
//        Log.e("LocationListViewModel", "t = $t")
//    }
}