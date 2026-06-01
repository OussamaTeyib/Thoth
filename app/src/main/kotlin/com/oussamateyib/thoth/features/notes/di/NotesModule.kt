package com.oussamateyib.thoth.features.notes.di;

import com.oussamateyib.thoth.features.notes.data.local.NoteDao
import com.oussamateyib.thoth.features.notes.data.repository.NoteRepositoryImpl
import com.oussamateyib.thoth.features.notes.domain.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NotesModule {
    @Provides
    @Singleton
    fun provideNoteRepository(noteDao: NoteDao): NoteRepository {
        return NoteRepositoryImpl(noteDao)
    }
}