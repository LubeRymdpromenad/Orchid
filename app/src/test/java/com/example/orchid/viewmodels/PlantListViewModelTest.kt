package com.example.orchid.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.orchid.common.MockSchedulerProvider
import com.example.orchid.data.PlantData
import com.example.orchid.repository.PlantDataStore
import io.reactivex.Single
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.powermock.modules.junit4.PowerMockRunner


@RunWith(PowerMockRunner::class)
class PlantListViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var plantDataStore: PlantDataStore

    val mockSchedulerProvider = MockSchedulerProvider()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `check PlantDataStore is called - success`() {
        // Given
        val plantId = "test_id"
        val testPlant = PlantData(plantId, "", "", "")
        val plantList = listOf(testPlant)
        Mockito.`when`(plantDataStore.getPlants()).thenReturn(Single.just(plantList))

        // When
        val plantListViewModel = PlantListViewModel(plantDataStore, mockSchedulerProvider)

        // Then
        assertEquals(plantId, plantListViewModel.data.value?.first()?.plantData?.id)
    }
}
