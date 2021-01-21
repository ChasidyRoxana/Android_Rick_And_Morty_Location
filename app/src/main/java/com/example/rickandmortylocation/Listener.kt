package com.example.rickandmortylocation

import com.example.rickandmortylocation.model.Location
import java.lang.Exception

interface Listener {
    fun onSuccess(locationList: List<Location>)
    fun onError(e: Exception)
}