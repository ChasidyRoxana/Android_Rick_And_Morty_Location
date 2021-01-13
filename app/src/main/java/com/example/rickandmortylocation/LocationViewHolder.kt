package com.example.rickandmortylocation

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LocationViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.recyclerview_item, parent, false)) {

    private var locationName: TextView? = null
    private var locationType: TextView? = null

    init {
        locationName = itemView.findViewById(R.id.tvLocationName)
        locationType = itemView.findViewById(R.id.tvLocationType)
    }

    fun bind(locationInfo: LocationInfo) {
        locationName?.text = locationInfo.name
        locationType?.text = locationInfo.type
    }
}