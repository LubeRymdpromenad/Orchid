package com.example.orchid.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.orchid.databinding.ListItemUnsplashBinding
import com.example.orchid.viewstates.UnsplashItemViewState

class UnsplashViewHolder(private val binding: ListItemUnsplashBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(unsplashItemViewState: UnsplashItemViewState) {
        binding.viewState = unsplashItemViewState
        binding.executePendingBindings()
    }

    companion object {
        fun create(parent: ViewGroup): UnsplashViewHolder {
            val binding = ListItemUnsplashBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return UnsplashViewHolder(binding)
        }
    }
}
