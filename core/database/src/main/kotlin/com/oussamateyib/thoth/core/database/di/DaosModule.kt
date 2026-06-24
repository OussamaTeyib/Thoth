package com.oussamateyib.thoth.core.database.di

import com.oussamateyib.thoth.core.database.ThothDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DaosModule {
    @Provides
    fun provideNoteDao(
        database: ThothDatabase
    ) = database.noteDao()
}