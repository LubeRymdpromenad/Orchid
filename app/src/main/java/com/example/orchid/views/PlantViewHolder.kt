package com.example.orchid.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.orchid.data.PlantData
import com.example.orchid.databinding.ListItemPlantBinding
import com.example.orchid.viewstates.PlantItemViewState

class PlantViewHolder(private val binding: ListItemPlantBinding) :
    RecyclerView.ViewHolder(binding.root) {

    init {
        binding.setClickListener {
            binding.viewState?.plantData?.let { plantData ->
                navigateToPlant(plantData, it)
            }
        }
    }

    private fun navigateToPlant(
        plantData: PlantData,
        view: View
    ) {
        val direction = PlantListFragmentDirections.actionPlantListToPlantDetail(plantData)
        view.findNavController().navigate(direction)
    }

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
