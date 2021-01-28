package com.example.rickandmortylocation.interfaces

import com.example.rickandmortylocation.model.network.Location
import java.lang.Exception

interface RequestLocationPageListener {

    fun onSuccess(locationList: List<Location>)
    fun onError(e: Exception)
}