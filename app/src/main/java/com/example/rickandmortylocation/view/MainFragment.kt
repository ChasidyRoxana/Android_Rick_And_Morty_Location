package com.example.rickandmortylocation.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortylocation.App
import com.example.rickandmortylocation.adapter.ListAdapter
import com.example.rickandmortylocation.model.Location
import com.example.rickandmortylocation.R
import com.example.rickandmortylocation.interfaces.MainContract
import com.example.rickandmortylocation.presenter.Presenter
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(R.layout.fragment_main), MainContract.MainView {

    private lateinit var presenter: MainContract.MAinPresenter
    private var listAdapter: ListAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val app = requireActivity().application as App
        presenter = Presenter(this, app.repository)
        setUpAdapter()
        presenter.onCreate()
//        initListeners()
    }

    private fun setUpAdapter() {
        listAdapter = ListAdapter(::showToast)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = listAdapter
        }
    }

//    private fun initListeners() {
////        buttonNextPage.setOnClickListener { presenter.onButtonClicked() }
//    }

    override fun setRecyclerView(locationList: List<Location>) {
        listAdapter?.setListLocation(locationList)
    }

    override fun showToast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }

    override fun setProgressBarVisibility(visibility: Boolean) {
        progressBar.visibility = if (visibility) {
            ProgressBar.VISIBLE
        } else {
            ProgressBar.INVISIBLE
        }
    }

}