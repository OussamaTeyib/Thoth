package com.oussamateyib.thoth.features.notes.data.local

import androidx.room.RoomDatabase
import androidx.room.Database

@Database(
    entities = [NoteEntity::class],
    version = 1
)
abstract class NoteDatabase : RoomDatabase() {
    abstract val noteDao: NoteDao
}