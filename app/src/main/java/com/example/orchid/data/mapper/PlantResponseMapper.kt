package com.example.orchid.data.mapper

import com.example.orchid.api.responses.PlantsResponse
import com.example.orchid.common.Mapper
import com.example.orchid.data.PlantData

class PlantResponseMapper : Mapper<PlantsResponse, List<PlantData>> {
    override fun map(model: PlantsResponse): List<PlantData> {
        return model.results.map {
            PlantData(it.plantId, it.name, it.description, it.imageUrl)
        }
    }
}
