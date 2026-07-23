package com.oussamateyib.thoth.core.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.oussamateyib.thoth.core.database.dao.NoteDao
import org.junit.After
import org.junit.Before

abstract class ThothDatabaseTest {
    private lateinit var db: ThothDatabase
    protected lateinit var noteDao: NoteDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            ThothDatabase::class.java,
        ).build()
        noteDao = db.noteDao()
    }

    @After
    fun teardown() {
        db.close()
    }
}
