package com.example.rickandmortylocation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortylocation.adapter.viewholder.LocationViewHolder
import com.example.rickandmortylocation.R
import com.example.rickandmortylocation.model.network.Location

class LocationAdapter(private val showToast: (Int, Int, String) -> Unit) :
    RecyclerView.Adapter<LocationViewHolder>() {

    private val locations: MutableList<Location> = mutableListOf()

    override fun getItemCount(): Int =
        locations.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.recyclerview_item_card, parent, false)
        return LocationViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val location: Location = locations[position]
        holder.bind(location, showToast)
    }

    fun addItems(locations: List<Location>) {
        this.locations.addAll(locations)
        notifyDataSetChanged()
    }
}