package com.oussamateyib.thoth.feature.notes.di;

import com.oussamateyib.thoth.feature.notes.data.repository.NoteRepositoryImpl
import com.oussamateyib.thoth.feature.notes.domain.repository.NoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NotesModule {
    @Binds
    @Singleton
    abstract fun bindNoteRepository(impl: NoteRepositoryImpl): NoteRepository
}