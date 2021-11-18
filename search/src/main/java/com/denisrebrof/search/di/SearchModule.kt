package com.denisrebrof.search.di

import com.denisrebrof.search.data.JsonPlaceholderApi
import com.denisrebrof.search.data.SearchRepositoryDefaultImpl
import com.denisrebrof.search.domain.ISearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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