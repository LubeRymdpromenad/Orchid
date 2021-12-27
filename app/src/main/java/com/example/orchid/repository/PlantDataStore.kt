package com.example.orchid.repository

import com.example.orchid.data.PlantData
import io.reactivex.Single

interface PlantDataStore {
    fun getPlants(): Single<List<PlantData>>
}
