package com.example.rickandmortylocation.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortylocation.model.Location
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class LocationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(location: Location, showToast: (String) -> Unit) {
        itemView.tvLocationName.text = location.name
        itemView.tvLocationType.text = location.type
        itemView.setOnClickListener { showToast(generateText(location)) }
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
}