package com.example.orchid.repository

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.orchid.common.Mapper
import com.example.orchid.data.UnsplashData
import com.example.orchid.viewstates.UnsplashItemViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

private const val TAG = "UnsplashDataSource"

const val PER_PAGE = 20

class UnsplashDataSource(
    private val query: String,
    private val unsplashDatastore: UnsplashDatastore,
    private val unsplashItemViewStateMapper: Mapper<List<UnsplashData>, List<UnsplashItemViewState>>,
    private val compositeDisposable: CompositeDisposable) : PageKeyedDataSource<Int, UnsplashItemViewState>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, UnsplashItemViewState>) {
        getItems(callback)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, UnsplashItemViewState>) {
        val nextPageNo = params.key + 1
        getMoreItems(params.key, nextPageNo, callback)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, UnsplashItemViewState>) {
        val previousPageNo = if (params.key > 1) params.key - 1 else 0
        getMoreItems(params.key, previousPageNo, callback)
    }

    private fun getItems(callback: LoadInitialCallback<Int, UnsplashItemViewState>) {
        unsplashDatastore.searchPhotos(query, 1, PER_PAGE)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { unsplashItemViewStateMapper.map(it) }
            .subscribe(
                { result ->
                    callback.onResult(result, null, 2)
                },
                {
                    Log.e(TAG, "Failed to load unsplash items", it)
                }
            ).also { compositeDisposable.add(it) }
    }

    private fun getMoreItems(pageNo: Int, previousOrNextPageNo: Int, callback: LoadCallback<Int, UnsplashItemViewState>) {
        unsplashDatastore.searchPhotos(query, pageNo, PER_PAGE)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { unsplashItemViewStateMapper.map(it) }
            .subscribe(
                { result ->
                    callback.onResult(result, previousOrNextPageNo)
                },
                {
                    Log.e(TAG, "Failed to load unsplash items", it)
                }
            ).also { compositeDisposable.add(it) }
    }
}
