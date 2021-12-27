package com.example.orchid.viewmodels

import androidx.databinding.Bindable
import com.example.orchid.data.PlantData
import com.example.orchid.common.ObservableViewModel
import com.example.orchid.BR
import com.example.orchid.views.Navigator

class PlantDetailViewModel : ObservableViewModel() {

    var navigator: Navigator? = null

    @get:Bindable
    var plantData: PlantData? = null
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.plantData)
            }
        }

    fun searchForMore() {
        plantData?.name?.let {
            navigator?.searchUnsplash(it)
        }
    }
}
