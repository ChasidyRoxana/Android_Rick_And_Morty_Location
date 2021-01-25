package com.example.rickandmortylocation.interfaces

import androidx.annotation.PluralsRes
import com.example.rickandmortylocation.model.network.Location

interface MainContract {
    interface MainView {
        fun updateRecyclerView(locationList: List<Location>)
        fun clearScrollListeners() //
        fun showToast(@PluralsRes resId: Int, countResidents: Int, locationName: String)
        fun setReconnectionButtonVisibility(visibility: Boolean)
        fun setProgressBarVisibility(visibility: Boolean)
    }

    interface MAinPresenter {
        fun onCreate()
        fun onRequestNextLocations()
    }
}