package com.example.orchid.viewmodels

import android.view.View
import androidx.databinding.Bindable
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.findNavController
import com.example.orchid.data.PlantData
import com.example.orchid.common.ObservableViewModel
import com.example.orchid.BR
import com.example.orchid.views.PlantDetailFragmentDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlantDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
)  : ObservableViewModel() {

    val data: PlantData? = savedStateHandle.get<PlantData>(PLANT_DATA_SAVED_STATE_KEY)

    init {
        data?.let {
            plantData = it
        }
    }

    @get:Bindable
    var plantData: PlantData? = null
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.plantData)
            }
        }

    fun searchForMore(view: View) {
        plantData?.name?.let {
            val direction = PlantDetailFragmentDirections.actionPlantDetailToUnsplashList(it)
            view.findNavController().navigate(direction)
        }
    }

    companion object {
        private const val PLANT_DATA_SAVED_STATE_KEY = "plantData"
    }
}
