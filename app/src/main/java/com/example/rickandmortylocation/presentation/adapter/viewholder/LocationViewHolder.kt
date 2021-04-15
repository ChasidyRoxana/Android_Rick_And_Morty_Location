package com.example.rickandmortylocation.presentation.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortylocation.databinding.ViewHolderLocationItemBinding
import com.example.rickandmortylocation.presentation.model.LocationItem

class LocationViewHolder(private val binding: ViewHolderLocationItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(locationItem: LocationItem, onShowDetailClicked: (LocationItem) -> Unit) {
        binding.tvLocationName.text = locationItem.name
        binding.tvLocationType.text = locationItem.type
        binding.ivRightArrow.setOnClickListener { onShowDetailClicked(locationItem) }
    }
}