package com.oussamateyib.thoth.features.notes.di;

import com.oussamateyib.thoth.features.notes.data.repository.NoteRepositoryImpl
import com.oussamateyib.thoth.features.notes.domain.repository.NoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NotesModule {
    @Binds
    @Singleton
    abstract fun bindNoteRepository(impl: NoteRepositoryImpl): NoteRepository
}