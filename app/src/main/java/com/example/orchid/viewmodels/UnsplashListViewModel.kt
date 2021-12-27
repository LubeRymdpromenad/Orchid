package com.example.orchid.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.orchid.adapters.UnsplashAdapter
import com.example.orchid.common.Mapper
import com.example.orchid.data.UnsplashData
import com.example.orchid.repository.PER_PAGE
import com.example.orchid.repository.UnsplashDataSourceFactory
import com.example.orchid.repository.UnsplashDatastore
import com.example.orchid.viewstates.UnsplashItemViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

const val TAG = "UnsplashListViewModel"

@HiltViewModel
class UnsplashListViewModel @Inject constructor(
    private val unsplashDatastore: UnsplashDatastore,
    private val unsplashItemViewStateMapper: Mapper<List<UnsplashData>, List<UnsplashItemViewState>>) : ViewModel() {

    private lateinit var factory: UnsplashDataSourceFactory
    private val compositeDisposable = CompositeDisposable()
    var adapter: UnsplashAdapter = UnsplashAdapter()

    var data: LiveData<PagedList<UnsplashItemViewState>> = MutableLiveData()
        private set

    var query: String? = null
        set(value) {
            value?.let {
                factory = UnsplashDataSourceFactory(value, unsplashDatastore, unsplashItemViewStateMapper, compositeDisposable)

                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setPageSize(PER_PAGE)
                    .build()

                data = LivePagedListBuilder(factory, config).build()
            }
        }

    fun submitList(it: PagedList<UnsplashItemViewState>) {
        adapter.submitList(it)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
