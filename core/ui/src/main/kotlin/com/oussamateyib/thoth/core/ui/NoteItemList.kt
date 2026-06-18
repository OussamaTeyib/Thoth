package com.oussamateyib.thoth.core.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oussamateyib.thoth.core.model.data.Note

fun LazyListScope.noteItems(
    items: List<Note>,
    onNoteClick: (Int) -> Unit,
    onDeleteClick: (Note) -> Unit,
    modifier: Modifier = Modifier
) = items(
    items = items,
    key = { it.id!! }
) { note ->
    NoteItem(
        note = note,
        modifier = modifier
            .clickable {
                onNoteClick(note.id!!)
            },
        onDeleteClick = {
            onDeleteClick(note)
        }
    )
    Spacer(modifier = Modifier.height(16.dp))
}