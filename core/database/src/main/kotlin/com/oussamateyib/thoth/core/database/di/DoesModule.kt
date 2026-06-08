package com.oussamateyib.thoth.core.database.di

import com.oussamateyib.thoth.core.database.ThothDatabase
import com.oussamateyib.thoth.core.database.dao.NoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DoesModule {
    @Provides
    fun provideNoteDao(
        database: ThothDatabase
    ): NoteDao {
        return database.noteDao()
    }
}