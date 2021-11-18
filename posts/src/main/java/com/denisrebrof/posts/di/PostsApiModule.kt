package com.denisrebrof.posts.di

import com.denisrebrof.posts.data.PostsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PostsApiModule {
    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): PostsApi = retrofit.create(PostsApi::class.java)

}