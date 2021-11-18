package com.denisrebrof.composetest.di

import com.denisrebrof.composetest.data.JsonPlaceholderApi
import com.denisrebrof.composetest.data.SearchRepositoryDefaultImpl
import com.denisrebrof.composetest.domain.ISearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchModule {

    @Singleton
    @Provides
    fun provideRepository(repository: SearchRepositoryDefaultImpl): ISearchRepository = repository

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): JsonPlaceholderApi = retrofit.create(JsonPlaceholderApi::class.java)
}