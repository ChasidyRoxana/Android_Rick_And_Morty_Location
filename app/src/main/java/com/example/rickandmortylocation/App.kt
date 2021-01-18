package com.example.rickandmortylocation

import android.app.Application
import com.example.rickandmortylocation.network.NetworkService

class App : Application() {

    val networkService = NetworkService()

    override fun onCreate() {
        super.onCreate()

    }
}