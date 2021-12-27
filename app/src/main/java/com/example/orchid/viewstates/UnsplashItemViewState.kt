package com.example.orchid.viewstates

import android.content.Intent
import android.net.Uri
import android.view.View

data class UnsplashItemViewState(
    val id: String,
    val name: String,
    val imageUrl: String,
    val attributionUrl: String) {

    fun onClick(view: View) {
        val uri = Uri.parse(attributionUrl)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        view.context.startActivity(intent)
    }
}
