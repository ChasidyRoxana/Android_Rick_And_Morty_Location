package com.example.rickandmortylocation.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmortylocation.presentation.command.BaseCommand
import com.example.rickandmortylocation.presentation.model.BaseScreenState

abstract class BaseViewModel<ScreenState : BaseScreenState, Command : BaseCommand>(initState: ScreenState) :
    ViewModel() {

    protected var model: ScreenState = initState

    private val modelLiveData = MutableLiveData<ScreenState>()
    private val commandLiveData = MutableLiveData<Command>()

    val modelEvent: LiveData<ScreenState>
        get() = modelLiveData
    val commandEvent: LiveData<Command>
        get() = commandLiveData

    protected fun refreshView() {
        modelLiveData.value = model
    }

    protected fun executeCommand(command: Command) {
        commandLiveData.value = command
    }
}