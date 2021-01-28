package com.example.rickandmortylocation

import android.app.Application
import com.example.rickandmortylocation.model.Repository
import com.example.rickandmortylocation.model.network.NetworkService

class App : Application() {

    private val networkService = NetworkService()
    val repository = Repository(networkService)

}