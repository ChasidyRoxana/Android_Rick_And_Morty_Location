package com.example.rickandmortylocation.presentation.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmortylocation.R
import com.example.rickandmortylocation.databinding.FragmentLocationListBinding
import com.example.rickandmortylocation.presentation.adapter.LocationAdapter
import com.example.rickandmortylocation.presentation.command.LocationListCommand
import com.example.rickandmortylocation.presentation.model.LocationItem
import com.example.rickandmortylocation.presentation.model.LocationListScreenState
import com.example.rickandmortylocation.presentation.model.MainContract
import com.example.rickandmortylocation.presentation.viewmodel.LocationListViewModel

class LocationListFragment :
    BaseFragment<LocationListScreenState, LocationListCommand, LocationListViewModel>(
        R.layout.fragment_location_list,
        LocationListViewModel::class.java
    ) {

    private val binding by viewBinding(FragmentLocationListBinding::bind)
    private var locationAdapter: LocationAdapter? = null

    private lateinit var presenter: MainContract.MainPresenter // todo delete

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val app = requireActivity().application as App
//        presenter = Presenter(this, app.repository)
        initView()
        presenter.onCreate()
        viewModel.init()
    }

    override fun renderView(model: LocationListScreenState) {
        setErrorMessageVisible(model.isErrorMessageVisible)
    }

    override fun executeCommand(command: LocationListCommand) {
        when (command) {
            is LocationListCommand.NavigateToLocationDetailScreen -> navigateToLocationDetailScreen(command.locationId)
        }
    }

    private fun setErrorMessageVisible(isVisible: Boolean) {
        binding.bError.isVisible = isVisible
    }

    private fun navigateToLocationDetailScreen(locationId: String) {
        val args = LocationDetailFragmentArgs(locationId).toBundle()
        navController.navigate(R.id.action_locationListFragment_to_locationDetailFragment, args)
    }

    private fun initView() {
        setupAdapter()
        initListeners()
    }

    private fun setupAdapter() {
        locationAdapter = LocationAdapter(viewModel::onShowDetailClicked)
        binding.recyclerView.adapter = locationAdapter
    }

    private fun initListeners() {
//        binding.bError.setOnClickListener { presenter.onReconnectionButtonClicked() }

//        binding.recyclerView.addOnScrollListener(createOnScrollListener())
    }

    fun showToast(locationName: String, countResidents: Int) {
        val stringRes = R.string.location_clicked
        val pluralRes = R.plurals.residents_in_location
        val residentsText = resources.getQuantityString(pluralRes, countResidents, countResidents)
        val text = resources.getString(stringRes, locationName, residentsText)
        context.run {
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
        }
    }

    fun addItems(locations: List<LocationItem>) {
        locationAdapter?.addItems(locations)
    }

    fun setProgressBarVisibility(isVisible: Boolean) {
        binding.progressBar.isVisible = isVisible
    }

    private fun createOnScrollListener() =
        object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val itemCount: Int = layoutManager.itemCount
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                if (lastVisibleItemPosition == itemCount - 1) {
                    presenter.onScrolledToLastElement()
                }
            }
        }

    private fun clearListeners() {
        binding.recyclerView.clearOnScrollListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onDestroyView()
        clearListeners()
        locationAdapter = null
    }
}