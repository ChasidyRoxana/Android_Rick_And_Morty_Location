package com.example.rickandmortylocation

import android.app.Application
import com.example.rickandmortylocation.model.Repository
import com.example.rickandmortylocation.network.NetworkService

class App : Application() {

    private val networkService = NetworkService()
    val repository = Repository(networkService)

//    override fun onCreate() {
//        super.onCreate()
//
//    } // todo delete
}