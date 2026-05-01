package com.oussamateyib.thoth.feature_note.data.data_source

import androidx.room.RoomDatabase
import androidx.room.Database
import com.oussamateyib.thoth.feature_note.domain.model.Note

@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteDatabase : RoomDatabase() {
    abstract val noteDao: NoteDao
}