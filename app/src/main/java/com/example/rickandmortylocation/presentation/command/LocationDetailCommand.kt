package com.example.rickandmortylocation.presentation.command

sealed class LocationDetailCommand : BaseCommand {
    object NavigateToPreviousScreen : LocationDetailCommand()
}