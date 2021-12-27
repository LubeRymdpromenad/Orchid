package com.example.orchid.data.mapper

import com.example.orchid.api.responses.UnsplashSearchResponse
import com.example.orchid.common.Mapper
import com.example.orchid.data.UnsplashData

class UnsplashResponseMapper : Mapper<UnsplashSearchResponse, List<UnsplashData>> {
    override fun map(model: UnsplashSearchResponse): List<UnsplashData> {
        return mutableListOf<UnsplashData>().also { list ->
            model.results?.map {
                list.add(UnsplashData(it.id.orEmpty(), it.user?.name, it.urls?.small, it.user?.attributionUrl))
            }
        }
    }
}
