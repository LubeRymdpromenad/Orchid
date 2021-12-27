package com.example.orchid.api

import com.example.orchid.BuildConfig
import com.example.orchid.api.responses.PlantsResponse
import com.example.orchid.api.responses.UnsplashSearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

private const val DEFAULT_PAGE = 1
private const val DEFAULT_PER_PAGE = 20

interface PlantApi {
    @GET("search/photos")
    fun searchPhotos(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("client_id") clientId: String = BuildConfig.UNSPLASH_ACCESS_KEY
    ): Single<UnsplashSearchResponse>

    @GET("search/photos")
    fun getPlants(
        @Query("query") query: String,
        @Query("page") page: Int = DEFAULT_PAGE,
        @Query("per_page") perPage: Int = DEFAULT_PER_PAGE,
        @Query("client_id") clientId: String = BuildConfig.UNSPLASH_ACCESS_KEY
    ): Single<PlantsResponse>
}
