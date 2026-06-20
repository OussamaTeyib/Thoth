package com.oussamateyib.thoth.core.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oussamateyib.thoth.core.model.data.Note

fun LazyListScope.noteItems(
    items: List<Note>,
    selectedItems: Set<Int>,
    onNoteClick: (Int) -> Unit,
    onNoteLongClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) = items(
    items = items,
    key = { it.id!! }
) { note ->
    NoteItem(
        note = note,
        isSelected = selectedItems.contains(note.id),
        modifier = modifier,
        onClick = { onNoteClick(note.id!!) },
        onLongClick = { onNoteLongClick(note.id!!) }
    )
    Spacer(modifier = Modifier.height(16.dp))
}