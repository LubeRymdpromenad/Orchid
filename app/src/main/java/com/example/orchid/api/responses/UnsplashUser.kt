package com.example.orchid.api.responses

data class UnsplashUser(
    val name: String? = null,
    val username: String? = null) {

    val attributionUrl: String
        get() {
            return "https://unsplash.com/$username?utm_source=sunflower&utm_medium=referral"
        }
}
