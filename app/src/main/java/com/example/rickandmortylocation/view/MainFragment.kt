package com.example.rickandmortylocation.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortylocation.App
import com.example.rickandmortylocation.adapter.ListAdapter
import com.example.rickandmortylocation.model.Location
import com.example.rickandmortylocation.R
import com.example.rickandmortylocation.interfaces.MainContract
import com.example.rickandmortylocation.presenter.Presenter
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(R.layout.fragment_main), MainContract.MainView {

    private lateinit var presenter: MainContract.MAinPresenter
    private lateinit var listAdapter: ListAdapter
    private lateinit var layoutManager: LinearLayoutManager

//    private val onScrollListener: RecyclerView.OnScrollListener = createScrollListener()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val app = requireActivity().application as App
        presenter = Presenter(this, app.repository)
        setUpAdapter()
        presenter.onCreate()
        initListeners()
    }

    private fun setUpAdapter() {
        listAdapter = ListAdapter(::showToast)
        layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = listAdapter
        recyclerView.layoutManager = layoutManager
    }

    private fun initListeners() {
        recyclerView.addOnScrollListener(createScrollListener())
    }

    private fun createScrollListener(): RecyclerView.OnScrollListener =
        object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val itemCount: Int = layoutManager.itemCount
                val lastVisibleItem: Int = layoutManager.findLastVisibleItemPosition()
                Log.i("TAG", "$itemCount, $lastVisibleItem")
                if (lastVisibleItem >= itemCount - 1) {
                    presenter.onLoadNextLocation()
                }
            }
        }

    override fun removeScrollListeners() {
        recyclerView.clearOnScrollListeners()
    }

    override fun setRecyclerView(locationList: List<Location>) {
        listAdapter.setListLocation(locationList)
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