package com.example.rickandmortylocation.presentation.viewmodel

import com.example.rickandmortylocation.presentation.command.LocationDetailCommand
import com.example.rickandmortylocation.presentation.model.LocationDetailScreenState

class LocationDetailViewModel : BaseViewModel<LocationDetailScreenState, LocationDetailCommand>(
    LocationDetailScreenState()
) {
}