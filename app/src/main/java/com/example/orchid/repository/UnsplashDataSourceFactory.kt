package com.example.orchid.repository

import androidx.paging.DataSource
import com.example.orchid.common.Mapper
import com.example.orchid.data.UnsplashData
import com.example.orchid.viewstates.UnsplashItemViewState
import io.reactivex.disposables.CompositeDisposable

class UnsplashDataSourceFactory(
    private val query: String,
    private val unsplashDatastore: UnsplashDatastore,
    private val unsplashItemViewStateMapper: Mapper<List<UnsplashData>, List<UnsplashItemViewState>>,
    private val compositeDisposable: CompositeDisposable
) : DataSource.Factory<Int, UnsplashItemViewState>() {
    override fun create(): DataSource<Int, UnsplashItemViewState> {
        return UnsplashDataSource(query, unsplashDatastore, unsplashItemViewStateMapper, compositeDisposable)
    }
}
