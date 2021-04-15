package com.example.rickandmortylocation.presentation.model

interface RequestLocationPageListener {

    fun onSuccess(locations: List<LocationItem>)
    fun onError(t: Throwable)
}