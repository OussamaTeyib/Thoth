package com.oussamateyib.thoth.core.database.di

import android.content.Context
import androidx.room.Room
import com.oussamateyib.thoth.core.database.ThothDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {
    @Provides
    @Singleton
    fun provideThothDatabase(
        @ApplicationContext context: Context
    ): ThothDatabase = Room.databaseBuilder(
        context,
        ThothDatabase::class.java,
        "thoth-database"
    ).build()
}