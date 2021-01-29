package com.example.rickandmortylocation.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.annotation.PluralsRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortylocation.App
import com.example.rickandmortylocation.presentation.adapter.LocationAdapter
import com.example.rickandmortylocation.data.network.models.Location
import com.example.rickandmortylocation.R
import com.example.rickandmortylocation.presentation.interfaces.MainContract
import com.example.rickandmortylocation.presentation.presenter.Presenter
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(R.layout.fragment_main), MainContract.MainView {

    private lateinit var presenter: MainContract.MainPresenter
    private var locationAdapter: LocationAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val app = requireActivity().application as App
        presenter = Presenter(this, app.repository)
        presenter.onCreate()
        initListeners()
    }

    private fun initListeners() {
        bReconnection.setOnClickListener { presenter.onReconnectionButtonClicked() }
    }

    override fun setupAdapter() {
        locationAdapter = LocationAdapter(presenter::onItemClicked)
        recyclerView.adapter = locationAdapter
    }

    override fun showToast(@PluralsRes pluralsId: Int, countResidents: Int, locationName: String) {
        val text =
            resources.getQuantityString(pluralsId, countResidents, locationName, countResidents)
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }

    override fun addItemsToRecyclerView(locations: List<Location>) {
        locationAdapter?.addItems(locations)
    }

    override fun addOnScrollListener() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val itemCount: Int = layoutManager.itemCount
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                if (lastVisibleItemPosition == itemCount - 1) {
                    presenter.onScrolledToLastElement()
                }
            }
        })
    }

    override fun clearOnScrollListeners() {
        recyclerView.clearOnScrollListeners()
    }

    override fun setReconnectionButtonVisibility(isVisible: Boolean) {
        bReconnection.visibility = if (isVisible) {
            View.VISIBLE
        } else {
            View.INVISIBLE
        }
    }

    override fun setProgressBarVisibility(isVisible: Boolean) {
        progressBar.visibility = if (isVisible) {
            View.VISIBLE
        } else {
            View.INVISIBLE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        locationAdapter = null
    }
}