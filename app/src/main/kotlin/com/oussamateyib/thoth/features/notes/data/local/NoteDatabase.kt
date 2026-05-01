package com.oussamateyib.thoth.features.notes.data.local

import androidx.room.RoomDatabase
import androidx.room.Database
import com.oussamateyib.thoth.features.notes.domain.model.Note

@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteDatabase : RoomDatabase() {
    abstract val noteDao: NoteDao
}