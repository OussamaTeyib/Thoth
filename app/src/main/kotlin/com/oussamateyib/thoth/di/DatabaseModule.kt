package com.oussamateyib.thoth.di

import android.app.Application
import androidx.room.Room
import com.oussamateyib.thoth.core.database.ThothDatabase
import com.oussamateyib.thoth.feature.notes.impl.data.local.NoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(app: Application): ThothDatabase {
        return Room.databaseBuilder(
            app,
            ThothDatabase::class.java,
            ThothDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideNoteDao(db: ThothDatabase): NoteDao {
        return db.noteDao
    }
}