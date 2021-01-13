package com.example.rickandmortylocation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(R.layout.fragment_main) {

    private val listLocation = listOf(
        LocationInfo(1, "Earth", "Planet", "dimension", null, "url", "created"),
        LocationInfo(2, "Earth2", "Planet2", "dimension", null, "url", "created"),
        LocationInfo(3, "Earth3", "Planet3", "dimension", null, "url", "created"),
        LocationInfo(4, "Earth4", "Planet4", "dimension", null, "url", "created"),
        LocationInfo(5, "Earth5", "Planet5", "dimension", null, "url", "created"),
        LocationInfo(6, "Earth6", "Planet6", "dimension", null, "url", "created"),
        LocationInfo(7, "Earth7", "Planet7", "dimension", null, "url", "created"),
        LocationInfo(8, "Earth8", "Planet8", "dimension", null, "url", "created"),
        LocationInfo(9, "Earth9", "Planet9", "dimension", null, "url", "created"),
        LocationInfo(10, "Earth10", "Planet10", "dimension", null, "url", "created"),
        LocationInfo(11, "Earth11", "Planet11", "dimension", null, "url", "created"),
        LocationInfo(12, "Earth12", "Planet12", "dimension", null, "url", "created"),
        LocationInfo(13, "Earth13", "Planet13", "dimension", null, "url", "created"),
        LocationInfo(14, "Earth14", "Planet14", "dimension", null, "url", "created"),
        LocationInfo(15, "Earth15", "Planet15", "dimension", null, "url", "created"),
        LocationInfo(16, "Earth16", "Planet16", "dimension", null, "url", "created")
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = ListAdapter(listLocation)
        }
    }

}