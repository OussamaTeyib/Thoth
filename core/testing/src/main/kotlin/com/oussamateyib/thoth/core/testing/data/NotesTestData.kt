package com.oussamateyib.thoth.core.testing.data

import com.oussamateyib.thoth.core.model.data.Note
import com.oussamateyib.thoth.core.model.data.NoteColor
import kotlin.time.Instant

val notesTestData = listOf(
    Note(
        id = 1,
        title = "Android Architecture",
        content = "Clean Architecture is great.",
        createdAt = Instant.fromEpochMilliseconds(1000),
        updatedAt = Instant.fromEpochMilliseconds(5000),
        color = NoteColor.SkyBlue,
    ),
    Note(
        id = 7,
        title = "Better Testing",
        content = "Unit tests are the foundation.",
        createdAt = Instant.fromEpochMilliseconds(2000),
        updatedAt = Instant.fromEpochMilliseconds(4000),
        color = NoteColor.ButterYellow,
    ),
    Note(
        id = 3,
        title = "Compose Basics",
        content = "Declarative UI is the future.",
        createdAt = Instant.fromEpochMilliseconds(3000),
        updatedAt = Instant.fromEpochMilliseconds(3000),
        color = NoteColor.MintGreen,
    ),
)
