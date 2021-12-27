package com.example.orchid.di

import android.content.Context
import com.example.orchid.BuildConfig
import com.example.orchid.api.PlantApi
import com.example.orchid.api.responses.PlantsResponse
import com.example.orchid.api.responses.UnsplashSearchResponse
import com.example.orchid.common.Mapper
import com.example.orchid.common.SchedulerProvider
import com.example.orchid.data.*
import com.example.orchid.data.mapper.PlantResponseMapper
import com.example.orchid.data.mapper.UnsplashItemViewStateMapper
import com.example.orchid.data.mapper.UnsplashResponseMapper
import com.example.orchid.repository.PlantDataStore
import com.example.orchid.repository.PlantRepository
import com.example.orchid.repository.UnsplashDatastore
import com.example.orchid.repository.UnsplashRepository
import com.example.orchid.viewstates.UnsplashItemViewState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideBaseUrl() = Constants.BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor().also {
            it.setLevel(HttpLoggingInterceptor.Level.BODY)
        }

        OkHttpClient
            .Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient
            .Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    @Provides
    @Singleton
    fun providePlantApi(retrofit: Retrofit): PlantApi = retrofit.create(PlantApi::class.java)

    @Provides
    @Singleton
    fun provideSchedulersProvider(): SchedulerProvider {
        return object : SchedulerProvider {
            override fun ui(): Scheduler {
                return AndroidSchedulers.mainThread()
            }

            override fun computation(): Scheduler {
                return Schedulers.computation()
            }

            override fun io(): Scheduler {
                return Schedulers.io()
            }
        }
    }

    @Provides
    @Singleton
    fun providePlantDataStore(@ApplicationContext context: Context, plantApi: PlantApi, plantDataResponseMapper: Mapper<PlantsResponse, List<PlantData>>): PlantDataStore {
        return PlantRepository(context, plantApi, plantDataResponseMapper)
    }

    @Provides
    @Singleton
    fun provideUnsplashDataStore(plantApi: PlantApi, unsplashResponseMapper: Mapper<UnsplashSearchResponse, List<UnsplashData>>): UnsplashDatastore {
        return UnsplashRepository(plantApi, unsplashResponseMapper)
    }

    @Provides
    @Singleton
    fun providePlantResponseMapper(): Mapper<PlantsResponse, List<PlantData>> = PlantResponseMapper()

    @Provides
    @Singleton
    fun provideUnsplashResponseMapper(): Mapper<UnsplashSearchResponse, List<UnsplashData>> = UnsplashResponseMapper()

    @Provides
    @Singleton
    fun provideUnsplashItemViewStateMapper(): Mapper<List<UnsplashData>, List<UnsplashItemViewState>> = UnsplashItemViewStateMapper()
}

object Constants {
    const val BASE_URL = "https://api.unsplash.com/"
}