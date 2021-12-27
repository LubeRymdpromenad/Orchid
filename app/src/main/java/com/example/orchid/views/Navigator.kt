package com.example.orchid.views

import com.example.orchid.data.PlantData

interface Navigator {
    fun openDetails(plantData: PlantData)
    fun searchUnsplash(query: String)
}