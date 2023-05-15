package com.mobilez.infrastructure.repositories

import com.mobilez.fizzbuzz.domain.repositories.WordRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesHiltModule {
    @Singleton
    @Binds
    internal abstract fun bindsWordRepository(impl: WordRepositoryImpl): WordRepository
}
