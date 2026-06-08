package com.oussamateyib.thoth.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.oussamateyib.thoth.core.database.dao.NoteDao
import com.oussamateyib.thoth.core.database.model.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = 1
)
internal abstract class ThothDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}