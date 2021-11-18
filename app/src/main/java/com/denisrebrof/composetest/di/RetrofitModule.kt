package com.denisrebrof.composetest.di

import com.denisrebrof.composetest.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val converterFactory = GsonConverterFactory.create()
        return Retrofit
            .Builder()
            .baseUrl(BuildConfig.SEARCH_API_URL)
            .addConverterFactory(converterFactory)
            .build()
    }
}