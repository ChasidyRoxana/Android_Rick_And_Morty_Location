package com.example.rickandmortylocation.presentation.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortylocation.App
import com.example.rickandmortylocation.R
import com.example.rickandmortylocation.domain.models.Location
import com.example.rickandmortylocation.presentation.adapter.LocationAdapter
import com.example.rickandmortylocation.presentation.interfaces.MainContract
import com.example.rickandmortylocation.presentation.presenter.Presenter
import kotlinx.android.synthetic.main.fragment_location_list.*

class LocationListFragment : Fragment(R.layout.fragment_location_list), MainContract.MainView {

    private lateinit var presenter: MainContract.MainPresenter
    private var locationAdapter: LocationAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val app = requireActivity().application as App
        presenter = Presenter(this, app.repository)
        initView()
        presenter.onCreate()
    }

    private fun initView() {
        setupAdapter()
        initListeners()
    }

    private fun setupAdapter() {
        locationAdapter = LocationAdapter(presenter::onItemClicked)
        recyclerView.adapter = locationAdapter
    }

    private fun initListeners() {
        bReconnect.setOnClickListener { presenter.onReconnectionButtonClicked() }

        recyclerView.addOnScrollListener(createOnScrollListener())
    }

    override fun showToast(locationName: String, countResidents: Int) {
        val stringRes = R.string.location_clicked
        val pluralRes = R.plurals.residents_in_location
        val residentsText = resources.getQuantityString(pluralRes, countResidents, countResidents)
        val text = resources.getString(stringRes, locationName, residentsText)
        context.run {
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
        }
//        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }

    override fun addItems(locations: List<Location>) {
        locationAdapter?.addItems(locations)
    }

    override fun setReconnectionButtonVisibility(isVisible: Boolean) {
        bReconnect.isVisible = isVisible
    }

    override fun setProgressBarVisibility(isVisible: Boolean) {
        progressBar.isVisible = isVisible
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
        recyclerView.clearOnScrollListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onDestroyView()
        clearListeners()
        locationAdapter = null
    }
}