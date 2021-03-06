package com.example.rickandmortylocation.presentation.model

import com.example.rickandmortylocation.domain.model.Location

interface MainContract {

    interface MainView {
        fun addItems(locations: List<Location>)
        fun showToast(locationName: String, countResidents: Int)
        fun setReconnectionButtonVisibility(isVisible: Boolean)
        fun setProgressBarVisibility(isVisible: Boolean)
    }

    interface MainPresenter {
        fun onCreate()
        fun onScrolledToLastElement()
        fun onItemClicked(location: Location)
        fun onReconnectionButtonClicked()
        fun onDestroyView()
    }
}