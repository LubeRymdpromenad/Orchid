package com.example.orchid.data.mapper

import com.example.orchid.api.responses.*
import io.mockk.verify
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.powermock.modules.junit4.PowerMockRunner


@RunWith(PowerMockRunner::class)
class UnsplashResponseMapperTest {

    val mapper = UnsplashResponseMapper()

    @Mock
    lateinit var unsplashPhoto: UnsplashPhoto

    @Mock
    lateinit var unsplashUser: UnsplashUser

    @Mock
    lateinit var unsplashPhotoUrls: UnsplashPhotoUrls

    @Mock
    lateinit var unsplashSearchResponse: UnsplashSearchResponse


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `convert response to data list - success`() {
        // Given
        val id = "3"
        Mockito.`when`(unsplashPhoto.id).thenReturn(id)
        Mockito.`when`(unsplashPhoto.user).thenReturn(unsplashUser)

        val name = "Lars"
        Mockito.`when`(unsplashUser.name).thenReturn(name)

        val userName = "larvik"
        Mockito.`when`(unsplashUser.username).thenReturn(userName)

        Mockito.`when`(unsplashPhoto.urls).thenReturn(unsplashPhotoUrls)

        val smallUrl = "small_test_url"
        Mockito.`when`(unsplashPhotoUrls.small).thenReturn(smallUrl)

        val attributionUrl = "attribution_test_url"
        Mockito.`when`(unsplashUser.attributionUrl).thenReturn(attributionUrl)

        val photos = mutableListOf(unsplashPhoto)
        Mockito.`when`(unsplashSearchResponse.results).thenReturn(photos)

        // When
        val unsplashDataList = mapper.map(unsplashSearchResponse)

        // Then
        assertTrue(unsplashDataList.first().id == id)
        assertTrue(unsplashDataList.first().name == name)
        assertTrue(unsplashDataList.first().imageUrl == smallUrl)
        assertTrue(unsplashDataList.first().attributionUrl == attributionUrl)
    }

//    @Test
//    fun `convert response to data list - success`() {
//
//        // Given
//        val unsplashUser = UnsplashUser("Lars", "larvik")
//        val unsplashPhotoUrls = UnsplashPhotoUrls("test_url")
//        val unsplashPhoto = UnsplashPhoto("testid", unsplashPhotoUrls, unsplashUser)
//        val photos = mutableListOf(unsplashPhoto)
//        val unsplashSearchResponse = UnsplashSearchResponse(photos, 1)
//
//        // When
//        val list = mapper.map(unsplashSearchResponse)
//
//        // Then
//        assertTrue(list.first().id == unsplashPhoto.id)
//        assertTrue(list.first().imageUrl == unsplashPhotoUrls.small)
//        assertTrue(list.first().name == unsplashUser.name)
//    }
}
