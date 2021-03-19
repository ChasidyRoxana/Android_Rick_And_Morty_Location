package com.example.rickandmortylocation.presentation.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortylocation.domain.models.Location
import kotlinx.android.synthetic.main.list_item_briefly_card.view.*

class LocationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(location: Location, onItemClicked: (Location) -> Unit) {
        itemView.tvLocationName.text = location.name
        itemView.tvLocationType.text = location.type
        itemView.setOnClickListener { onItemClicked(location) }
    }
}