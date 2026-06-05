package com.oussamateyib.thoth.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.oussamateyib.thoth.feature.notes.impl.data.local.NoteDao
import com.oussamateyib.thoth.feature.notes.impl.data.local.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = 1
)
abstract class ThothDatabase : RoomDatabase() {
    abstract val noteDao: NoteDao

    companion object {
        val DATABASE_NAME = "thoth.db"
    }
}