package com.example.rickandmortylocation.presenter

import com.example.rickandmortylocation.interfaces.LoadPageListener
import com.example.rickandmortylocation.interfaces.MainContract
import com.example.rickandmortylocation.model.Location
import com.example.rickandmortylocation.model.Repository
import java.lang.Exception

class Presenter(private val view: MainContract.MainView, private val repository: Repository) :
    MainContract.MAinPresenter {

    private var loading: Boolean = false

    override fun onCreate() {
        val locations = repository.loadLocations()
        view.setRecyclerView(locations)

        onLoadNextLocation() // этого тут быть не должно
    }

    override fun onLoadNextLocation() {
        if (!loading) {
            loadingState(true)
            repository.setLoadPageListener(getLoadPageListener())
            repository.getNextLocations()
        }
    }

    private fun getLoadPageListener(): LoadPageListener =
        object : LoadPageListener {
            override fun onError(e: Exception) {
                view.showToast("Не загрузилось ничего")
                loadingState(false)
            }

            override fun onSuccess(locationList: List<Location>, moreData: Boolean) {
                view.setRecyclerView(locationList)
                view.showToast("Получены локации до номера ${locationList[locationList.lastIndex].id}")
                loadingState(false)
                if (!moreData) {
                    view.removeScrollListeners()
                }
            }
        }

    private fun loadingState(isLoading: Boolean) {
        view.setProgressBarVisibility(isLoading)
        loading = isLoading
    }
}