package com.example.orchid.viewstates

import com.example.orchid.data.PlantData

data class PlantItemViewState(val plantData: PlantData, var callback: ((PlantData) -> Unit)? = null) {
    fun onClick() {
        callback?.invoke(plantData)
    }
}
