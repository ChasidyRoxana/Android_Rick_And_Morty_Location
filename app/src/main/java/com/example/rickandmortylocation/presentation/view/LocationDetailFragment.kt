package com.example.rickandmortylocation.presentation.view

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmortylocation.R
import com.example.rickandmortylocation.databinding.FragmentLocationDetailBinding
import com.example.rickandmortylocation.presentation.command.LocationDetailCommand
import com.example.rickandmortylocation.presentation.model.LocationDetailScreenState
import com.example.rickandmortylocation.presentation.viewmodel.LocationDetailViewModel

class LocationDetailFragment :
    BaseFragment<LocationDetailScreenState, LocationDetailCommand, LocationDetailViewModel>(
        R.layout.fragment_location_detail,
        LocationDetailViewModel::class.java
    ) {

    private val binding by viewBinding(FragmentLocationDetailBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = LocationDetailFragmentArgs.fromBundle(requireArguments())
        // init viewModel
    }

    override fun renderView(model: LocationDetailScreenState) {
    }

    override fun executeCommand(command: LocationDetailCommand) {
    }

}