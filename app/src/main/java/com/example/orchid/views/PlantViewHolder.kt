package com.example.orchid.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.orchid.databinding.ListItemPlantBinding
import com.example.orchid.viewstates.PlantItemViewState

class PlantViewHolder(private val binding: ListItemPlantBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(plantItemViewState: PlantItemViewState) {
        binding.viewState = plantItemViewState
        binding.executePendingBindings()
    }

    companion object {
        fun create(parent: ViewGroup): PlantViewHolder {
            val binding =
                ListItemPlantBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return PlantViewHolder(binding)
        }
    }
}
