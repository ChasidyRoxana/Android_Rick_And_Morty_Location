package com.example.rickandmortylocation.presentation.interfaces

import com.example.rickandmortylocation.domain.models.Location
import java.lang.Exception

interface RequestLocationPageListener {

    fun onSuccess(locations: List<Location>)
    fun onError(e: Exception)
}