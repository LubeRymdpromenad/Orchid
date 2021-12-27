package com.example.orchid.repository

import android.content.Context
import com.example.orchid.R
import com.example.orchid.api.PlantApi
import com.example.orchid.api.responses.PlantsResponse
import com.example.orchid.common.Mapper
import com.example.orchid.common.jsonToClass
import com.example.orchid.data.PlantData
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.Single


// Used when rewrite to plants.json
private const val QUERY = "mock"

class PlantRepository(
    @ApplicationContext val context: Context,
    private val plantApi: PlantApi,
    private val plantDataResponseMapper: Mapper<PlantsResponse, List<PlantData>>
) : PlantDataStore {
    override fun getPlants(): Single<List<PlantData>> {
        //return plantApi.getPlants(QUERY).map { response ->
            //plantDataResponseMapper.map(response)
        //}
        val list: List<PlantData> = context.jsonToClass(R.raw.plants)
        return Single.just(list)
    }
}
