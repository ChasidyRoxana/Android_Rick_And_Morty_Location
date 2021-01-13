package com.example.rickandmortylocation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ListAdapter(private val listLocation: List<LocationInfo>) :
    RecyclerView.Adapter<LocationViewHolder>() {

    override fun getItemCount(): Int = listLocation.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return LocationViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val location: LocationInfo = listLocation[position]
        holder.bind(location)
    }
}