package com.example.rickandmortylocation.interfaces

import androidx.annotation.PluralsRes
import com.example.rickandmortylocation.model.network.Location

interface MainContract {

    interface MainView {
        fun setupAdapter()
        fun addItemsToRecyclerView(locations: List<Location>) // кажется этого тут быть не должно
        fun showToast(@PluralsRes resId: Int, countResidents: Int, locationName: String)
        fun addOnScrollListener()
        fun clearOnScrollListeners()
        fun setReconnectionButtonVisibility(isVisible: Boolean)
        fun setProgressBarVisibility(isVisible: Boolean)
    }

    interface MAinPresenter {
        fun onCreate()
        fun onScrolledToLastElement()
        fun onReconnectionButtonClicked()
    }
}