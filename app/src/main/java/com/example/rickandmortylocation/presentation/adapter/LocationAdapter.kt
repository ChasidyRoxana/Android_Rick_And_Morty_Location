package com.example.rickandmortylocation.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortylocation.databinding.ViewHolderLocationItemBinding
import com.example.rickandmortylocation.presentation.adapter.viewholder.LocationViewHolder
import com.example.rickandmortylocation.presentation.model.LocationItem

class LocationAdapter(private val onShowDetailClicked: (LocationItem) -> Unit) :
    RecyclerView.Adapter<LocationViewHolder>() {

    private val locations: MutableList<LocationItem> = mutableListOf()

    override fun getItemCount(): Int =
        locations.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderLocationItemBinding.inflate(layoutInflater, parent, false)
        return LocationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.bind(locations[position], onShowDetailClicked)
    }

    fun addItems(locationItems: List<LocationItem>) {
        this.locations.addAll(locationItems)
//        notifyDataSetChanged()
    }
}