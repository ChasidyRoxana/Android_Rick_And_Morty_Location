package com.example.rickandmortylocation.presentation.command

sealed class LocationListCommand: BaseCommand {
    class NavigateToLocationDetailScreen(val locationId: String) : LocationListCommand()
}