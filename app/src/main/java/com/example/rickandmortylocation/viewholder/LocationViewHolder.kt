package com.example.rickandmortylocation.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortylocation.R
import com.example.rickandmortylocation.model.Location
import kotlinx.android.synthetic.main.recyclerview_item_card.view.*

class LocationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(location: Location, showToast: (String) -> Unit) {
        itemView.tvLocationName.text = location.name
        itemView.tvLocationType.text = location.type
        itemView.setOnClickListener { showToast(generateText(location)) }
        itemView.containerInItem.setBackgroundResource(getResIdForLocation(location.id))
    }

    private fun generateText(location: Location): String {
        val countResidents: Int = location.residents?.size ?: 0
        val additionalText = when (countResidents) {
            0 -> "There is no resident in this location"
            1 -> "There is 1 resident in this location"
            else -> "There are $countResidents residents in this location"
        }
        return "${location.name} was clicked. " + additionalText
    }

    private fun getResIdForLocation(locationId: Int): Int = when (locationId % 10) {
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
