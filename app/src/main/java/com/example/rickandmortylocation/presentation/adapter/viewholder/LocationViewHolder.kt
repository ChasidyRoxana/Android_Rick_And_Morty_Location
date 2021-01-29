package com.example.rickandmortylocation.presentation.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortylocation.R
import com.example.rickandmortylocation.data.network.models.Location
import kotlinx.android.synthetic.main.recyclerview_item_card.view.*

class LocationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(location: Location, onItemClicked: (Location) -> Unit) {
        itemView.tvLocationName.text = location.name
        itemView.tvLocationType.text = location.type
        itemView.setOnClickListener { onItemClicked(location) }
    }
}
