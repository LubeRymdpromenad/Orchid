package com.example.orchid.repository

import com.example.orchid.data.UnsplashData
import io.reactivex.Single

interface UnsplashDatastore {
    fun searchPhotos(query: String, page: Int, perPage: Int): Single<List<UnsplashData>>
}