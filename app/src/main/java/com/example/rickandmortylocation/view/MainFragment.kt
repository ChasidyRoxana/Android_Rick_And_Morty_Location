package com.example.rickandmortylocation.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.annotation.PluralsRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortylocation.App
import com.example.rickandmortylocation.adapter.LocationAdapter
import com.example.rickandmortylocation.model.network.Location
import com.example.rickandmortylocation.R
import com.example.rickandmortylocation.interfaces.MainContract
import com.example.rickandmortylocation.presenter.Presenter
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(R.layout.fragment_main), MainContract.MainView {

    private lateinit var presenter: MainContract.MAinPresenter
    private lateinit var locationAdapter: LocationAdapter
    private lateinit var layoutManager: LinearLayoutManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("TAG", "create fragment")
        val app = requireActivity().application as App
        presenter = Presenter(this, app.repository)
        presenter.onCreate()
        initListeners()
    }

    private fun initListeners() {
        bReconnection.setOnClickListener { presenter.onReconnectionButtonClicked() }
    }

    override fun setupAdapter() {
        locationAdapter = LocationAdapter(::showToast)
        layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = locationAdapter
        recyclerView.layoutManager = layoutManager
    }

    override fun addItemsToRecyclerView(locations: List<Location>) {
        locationAdapter.addItems(locations)
    }

    override fun showToast(@PluralsRes resId: Int, countResidents: Int, locationName: String) {
        val text = resources.getQuantityString(resId, countResidents, locationName, countResidents)
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }

    override fun addOnScrollListener() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
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
        Log.i("TAG", "reconnection isVisible = $isVisible")
        bReconnection.visibility = if (isVisible) {
            View.VISIBLE
        } else {
            View.INVISIBLE
        }
    }

    override fun setProgressBarVisibility(isVisible: Boolean) {
        Log.i("TAG", "progress bar isVisible = $isVisible")
        progressBar.visibility = if (isVisible) {
            View.VISIBLE
        } else {
            View.INVISIBLE
        }
    }
}