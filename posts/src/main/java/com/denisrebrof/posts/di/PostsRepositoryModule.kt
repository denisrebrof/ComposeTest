package com.denisrebrof.posts.di

import com.denisrebrof.posts.data.PostsRepositoryImpl
import com.denisrebrof.posts.domain.model.IPostsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PostsRepositoryModule {
    @Singleton
    @Provides
    fun provideRepository(repository: PostsRepositoryImpl): IPostsRepository = repository

}