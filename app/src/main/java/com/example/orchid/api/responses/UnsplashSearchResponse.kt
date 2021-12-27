package com.example.orchid.api.responses

data class UnsplashSearchResponse(
    val results: List<UnsplashPhoto>? = null,
    var totalPages: Int? = null)