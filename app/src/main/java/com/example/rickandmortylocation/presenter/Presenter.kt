package com.example.rickandmortylocation.presenter

import com.example.rickandmortylocation.Listener
import com.example.rickandmortylocation.interfaces.MainContract
import com.example.rickandmortylocation.model.Location
import com.example.rickandmortylocation.model.Repository
import java.lang.Exception

class Presenter(private val view: MainContract.MainView, private val repository: Repository) :
    MainContract.MAinPresenter {

    private val listener = object : Listener {
        override fun onError(e: Exception) {
            view.showToast("Не загрузилось ничего")
//            view.setProgressBarVisibility(false)
        }

        override fun onSuccess(locationList: List<Location>) {
            view.setRecyclerView(locationList)
//            view.setProgressBarVisibility(false)
            view.showToast("Получены локации до номера ${locationList[locationList.lastIndex].id}")
        }
    }

    override fun onCreate() {
        val locations = repository.loadLocations()
        view.setRecyclerView(locations)
        repository.listener = listener
        view.setProgressBarVisibility(true)
        repository.getNextLocations() // этого тут быть не должно
    }

    override fun onButtonClicked() {
        view.setProgressBarVisibility(true)
        repository.getNextLocations()
    }
}