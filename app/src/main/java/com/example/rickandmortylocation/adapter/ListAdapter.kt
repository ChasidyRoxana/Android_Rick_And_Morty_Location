package com.example.rickandmortylocation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortylocation.viewholder.LocationViewHolder
import com.example.rickandmortylocation.R
import com.example.rickandmortylocation.model.Location

class ListAdapter(private val showToast: (String) -> Unit) :
    RecyclerView.Adapter<LocationViewHolder>() {

    private val listLocation: MutableList<Location> = mutableListOf()

    override fun getItemCount(): Int = listLocation.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.recyclerview_item_card, parent, false)
        return LocationViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val location: Location = listLocation[position]
        holder.bind(location, showToast)
    }

    fun setListLocation(listLocation: List<Location>) {
        this.listLocation.addAll(listLocation)
        notifyDataSetChanged()
    }
}