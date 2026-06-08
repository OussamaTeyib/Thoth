package com.oussamateyib.thoth.core.data.di

import com.oussamateyib.thoth.core.data.repository.NotesRepository
import com.oussamateyib.thoth.core.data.repository.OfflineFirstNotesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataModule {
    @Binds
    abstract fun bindNotesRepository(
        notesRepository: OfflineFirstNotesRepository
    ): NotesRepository
}