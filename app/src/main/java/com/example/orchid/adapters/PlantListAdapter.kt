package com.example.orchid.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.orchid.viewstates.PlantItemViewState
import com.example.orchid.views.PlantViewHolder

class PlantListAdapter : ListAdapter<PlantItemViewState, PlantViewHolder>(PlantDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        return PlantViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}

class PlantDiffCallback : DiffUtil.ItemCallback<PlantItemViewState>() {
    override fun areItemsTheSame(oldItem: PlantItemViewState, newItem: PlantItemViewState): Boolean {
        return oldItem.plantData.id == newItem.plantData.id
    }

    override fun areContentsTheSame(oldItem: PlantItemViewState, newItem: PlantItemViewState): Boolean {
        return oldItem == newItem
    }
}
