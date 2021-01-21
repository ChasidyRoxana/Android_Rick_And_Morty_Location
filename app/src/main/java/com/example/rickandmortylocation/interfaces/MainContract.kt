package com.example.rickandmortylocation.interfaces

import com.example.rickandmortylocation.model.Location

interface MainContract {
    interface MainView {
        fun setRecyclerView(locationList: List<Location>)

        fun showToast(text: String)//
        fun setProgressBarVisibility(visibility: Boolean)//
    }

    interface MAinPresenter {
        fun onCreate()

        fun onButtonClicked()//
    }
}