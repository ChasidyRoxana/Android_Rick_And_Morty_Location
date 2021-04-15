package com.example.rickandmortylocation.presentation.model

class LocationListScreenState(
    val locationItems: List<LocationItem> = emptyList(),
    val isErrorMessageVisible: Boolean = false
) : BaseScreenState {
}