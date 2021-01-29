package com.example.rickandmortylocation

import android.app.Application
import com.example.rickandmortylocation.data.repository.Repository
import com.example.rickandmortylocation.data.network.NetworkService

class App : Application() {

    private val networkService = NetworkService()
    private val apiService = networkService.createLocationApiService()
    val repository = Repository(apiService)

}