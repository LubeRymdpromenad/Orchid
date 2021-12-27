package com.example.orchid.viewmodels

import com.example.orchid.data.PlantData
import com.example.orchid.views.Navigator
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.powermock.modules.junit4.PowerMockRunner
import java.lang.IllegalArgumentException

@RunWith(PowerMockRunner::class)
class PlantDetailViewModelTest {

    @Mock
    lateinit var navigator: Navigator

    @Mock
    lateinit var plantData: PlantData

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun searchForMore() {
        // Given
        val plantDetailViewModel = PlantDetailViewModel()

        plantDetailViewModel.navigator = navigator
        plantDetailViewModel.plantData = plantData

        val testName = "testName"
        `when`(plantData.name).thenReturn(testName)

        // When
        plantDetailViewModel.searchForMore()

        // Then
        verify(navigator, times(1)).searchUnsplash(testName)
    }
}
