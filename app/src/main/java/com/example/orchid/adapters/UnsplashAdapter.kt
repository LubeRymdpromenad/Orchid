package com.example.orchid.adapters

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.orchid.viewstates.UnsplashItemViewState
import com.example.orchid.views.UnsplashViewHolder

class UnsplashAdapter : PagedListAdapter<UnsplashItemViewState, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    private val DATA_VIEW_TYPE = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return UnsplashViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == DATA_VIEW_TYPE)
            getItem(position)?.let { (holder as UnsplashViewHolder).bind(it) }
    }

    override fun getItemViewType(position: Int): Int {
        return DATA_VIEW_TYPE
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UnsplashItemViewState>() {
            override fun areItemsTheSame(oldItem: UnsplashItemViewState, newItem: UnsplashItemViewState): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: UnsplashItemViewState, newItem: UnsplashItemViewState): Boolean {
                return oldItem == newItem
            }
        }
    }
}
