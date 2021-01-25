package com.example.rickandmortylocation.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortylocation.R
import com.example.rickandmortylocation.model.network.Location
import kotlinx.android.synthetic.main.recyclerview_item_card.view.*

class LocationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(location: Location, showToast: (Int, Int, String) -> Unit) {
        val resId = R.plurals.residents_in_location
        val countResidents: Int = location.residents?.size ?: 0

        itemView.tvLocationName.text = location.name
        itemView.tvLocationType.text = location.type
        itemView.setOnClickListener { showToast(resId, countResidents, location.name) }
        itemView.containerInItem.setBackgroundResource(getColorResForLocation(location.id))
    }

    private fun getColorResForLocation(locationId: Int): Int = when (locationId % 10) {
        1 -> R.color.background_item_cyan
        2 -> R.color.background_item_yellow
        3 -> R.color.background_item_green
        4 -> R.color.background_item_magenta
        5 -> R.color.background_item_blue
        6 -> R.color.background_item_red
        7 -> R.color.background_item_orange
        8 -> R.color.background_item_pink
        9 -> R.color.background_item_light_green
        else -> R.color.background_item_gray
    }
}
