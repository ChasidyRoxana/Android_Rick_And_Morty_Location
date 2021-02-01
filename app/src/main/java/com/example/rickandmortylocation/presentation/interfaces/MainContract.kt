package com.example.rickandmortylocation.presentation.interfaces

import androidx.annotation.PluralsRes
import com.example.rickandmortylocation.data.network.models.Location

interface MainContract {

    interface MainView {
        fun setupAdapter()
        fun addItemsToRecyclerView(locations: List<Location>)
        fun showToast(@PluralsRes pluralsId: Int, countResidents: Int, locationName: String)
        fun addOnScrollListener()
        fun clearOnScrollListeners()
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