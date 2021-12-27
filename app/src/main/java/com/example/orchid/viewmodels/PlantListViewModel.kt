package com.example.orchid.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.orchid.adapters.PlantListAdapter
import com.example.orchid.common.SchedulerProvider
import com.example.orchid.data.PlantData
import com.example.orchid.repository.PlantDataStore
import com.example.orchid.views.Navigator
import com.example.orchid.viewstates.PlantItemViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


@HiltViewModel
class PlantListViewModel @Inject constructor(
    val plantDataStore: PlantDataStore,
    val schedulersProvider: SchedulerProvider
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val adapter = PlantListAdapter()

    var navigator: Navigator? = null

    private val _liveData = MutableLiveData<List<PlantItemViewState>>()
    val data: LiveData<List<PlantItemViewState>> = _liveData

    init {
        plantDataStore.getPlants()
            .subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.ui())
            .map { plants ->
                plants.map { PlantItemViewState(it) { plant -> navigator?.openDetails(plant) } }
            }
            .subscribe(
                { result ->
                    _liveData.postValue(result)
                    Log.i(TAG, "Success to load plants")
                },
                {
                    Log.e(TAG, "Failed to load plants", it)
                }
            ).also { compositeDisposable.add(it) }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
