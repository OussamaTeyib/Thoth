package com.oussamateyib.thoth.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.oussamateyib.thoth.core.database.dao.NoteDao
import com.oussamateyib.thoth.core.database.model.NoteEntity
import com.oussamateyib.thoth.core.database.util.InstantConverter

@Database(
    entities = [NoteEntity::class],
    version = 1
)
@TypeConverters(InstantConverter::class)
internal abstract class ThothDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}
