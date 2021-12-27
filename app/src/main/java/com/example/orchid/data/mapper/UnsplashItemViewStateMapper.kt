package com.example.orchid.data.mapper

import com.example.orchid.common.Mapper
import com.example.orchid.data.UnsplashData
import com.example.orchid.viewstates.UnsplashItemViewState

class UnsplashItemViewStateMapper : Mapper<List<UnsplashData>, List<UnsplashItemViewState>> {
    override fun map(model: List<UnsplashData>): List<UnsplashItemViewState> {
        return model.map { UnsplashItemViewState(it.id.orEmpty(), it.name.orEmpty(), it.imageUrl.orEmpty(), it.attributionUrl.orEmpty()) }
    }
}
