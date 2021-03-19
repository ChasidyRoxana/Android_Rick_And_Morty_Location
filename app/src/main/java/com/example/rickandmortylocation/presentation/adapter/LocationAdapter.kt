package com.example.rickandmortylocation.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortylocation.presentation.adapter.viewholder.LocationViewHolder
import com.example.rickandmortylocation.R
import com.example.rickandmortylocation.domain.models.Location

class LocationAdapter(private val onItemClicked: (Location) -> Unit) :
    RecyclerView.Adapter<LocationViewHolder>() {

    private val locations: MutableList<Location> = mutableListOf()

    override fun getItemCount(): Int =
        locations.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.list_item_briefly_card, parent, false)
        return LocationViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val location: Location = locations[position]
        holder.bind(location, onItemClicked)
    }

    fun addItems(locations: List<Location>) {
        this.locations.addAll(locations)
        notifyDataSetChanged()
    }
}