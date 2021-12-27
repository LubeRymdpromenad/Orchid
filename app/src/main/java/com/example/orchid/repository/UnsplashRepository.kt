package com.example.orchid.repository

import com.example.orchid.api.PlantApi
import com.example.orchid.api.responses.UnsplashSearchResponse
import com.example.orchid.common.Mapper
import com.example.orchid.data.UnsplashData
import io.reactivex.Single

class UnsplashRepository(private val plantApi: PlantApi, private val mapper: Mapper<UnsplashSearchResponse, List<UnsplashData>>) : UnsplashDatastore {
    override fun searchPhotos(query: String, page: Int, perPage: Int): Single<List<UnsplashData>> {
        return plantApi.searchPhotos(query, page, perPage).map { mapper.map(it) }
    }
}
