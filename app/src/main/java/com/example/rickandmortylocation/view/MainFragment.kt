package com.example.rickandmortylocation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortylocation.App
import com.example.rickandmortylocation.adapter.ListAdapter
import com.example.rickandmortylocation.model.Location
import com.example.rickandmortylocation.R
import com.example.rickandmortylocation.presenter.Presenter
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(R.layout.fragment_main) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val app = requireActivity().application as App
        val presenter = Presenter(this, app.networkService)
        presenter.onCreate()
    }

    fun setRecyclerView(locationList: List<Location>) {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = ListAdapter(locationList, ::showToast)
        }
    }

    fun showToast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }

}