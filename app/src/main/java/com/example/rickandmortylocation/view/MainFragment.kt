package com.example.rickandmortylocation.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ProgressBar
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
        val app = requireActivity().application as App
        presenter = Presenter(this, app.repository)
        setUpAdapter()
        presenter.onCreate()
        initListeners()
    }

    private fun setUpAdapter() {
        locationAdapter = LocationAdapter(::showToast)
        layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = locationAdapter
        recyclerView.layoutManager = layoutManager
    }

    private fun initListeners() {
        recyclerView.addOnScrollListener(createScrollListener())

        bReconnection.setOnClickListener { presenter.onRequestNextLocations() }
    }

    private fun createScrollListener(): RecyclerView.OnScrollListener =
        object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                Log.i("TAG", "scroll listener")
                val itemCount: Int = layoutManager.itemCount
                val lastVisibleItem: Int = layoutManager.findLastVisibleItemPosition()
                if (lastVisibleItem >= itemCount - 1) {
                    presenter.onRequestNextLocations()
                }
            }
        }

    override fun updateRecyclerView(locationList: List<Location>) {
        locationAdapter.updateListLocation(locationList)
    }

    override fun showToast(@PluralsRes resId: Int, countResidents: Int, locationName: String) {
        val text = resources.getQuantityString(resId, countResidents, locationName, countResidents)
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }

    override fun clearScrollListeners() {
        recyclerView.clearOnScrollListeners()
    }

    override fun setReconnectionButtonVisibility(visibility: Boolean) {
        bReconnection.visibility = if (visibility) {
            View.VISIBLE
        } else {
            View.INVISIBLE
        }
    }

    override fun setProgressBarVisibility(visibility: Boolean) {
        progressBar.visibility = if (visibility) {
            ProgressBar.VISIBLE
        } else {
            ProgressBar.INVISIBLE
        }
    }

}