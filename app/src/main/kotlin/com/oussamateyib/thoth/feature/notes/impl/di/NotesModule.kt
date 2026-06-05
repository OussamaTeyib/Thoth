package com.oussamateyib.thoth.feature.notes.impl.di

import com.oussamateyib.thoth.feature.notes.impl.data.repository.NoteRepositoryImpl
import com.oussamateyib.thoth.feature.notes.impl.domain.repository.NoteRepository
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