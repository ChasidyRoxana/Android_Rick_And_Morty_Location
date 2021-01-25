package com.example.rickandmortylocation.interfaces

import com.example.rickandmortylocation.model.Location
import java.lang.Exception

interface LoadPageListener {
    fun onSuccess(locationList: List<Location>, moreData: Boolean)
    fun onError(e: Exception)
}