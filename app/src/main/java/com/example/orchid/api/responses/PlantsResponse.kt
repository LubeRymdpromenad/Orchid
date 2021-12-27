package com.example.orchid.api.responses

import com.example.orchid.api.responses.PlantResponse
import com.google.gson.annotations.SerializedName

data class PlantsResponse (
    @field:SerializedName("results")
    val results: List<PlantResponse>
)
