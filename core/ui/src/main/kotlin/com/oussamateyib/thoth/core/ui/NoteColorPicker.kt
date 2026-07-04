package com.oussamateyib.thoth.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.oussamateyib.thoth.core.model.data.NoteColor
import com.oussamateyib.thoth.core.ui.util.PaletteLayout

@Composable
fun NoteColorPicker(
    onColorChange: (NoteColor) -> Unit,
    modifier: Modifier = Modifier,
    selectedColor: NoteColor? = null,
    layout: PaletteLayout = PaletteLayout.Grid
) = when (layout) {
    PaletteLayout.Row -> LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(NoteColor.entries) {
            ColorSwatch(it, it == selectedColor, { onColorChange(it) })
        }
    }

    PaletteLayout.Grid -> LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = modifier.padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(NoteColor.entries) {
            ColorSwatch(it, it == selectedColor, { onColorChange(it) })
        }
    }
}

@Composable
internal fun ColorSwatch(
    color: NoteColor,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val label = color.asLabel()

    Box(
        modifier = Modifier
            .size(60.dp)
            .clip(CircleShape)
            .selectable(
                selected = isSelected,
                onClick = onClick,
                role = Role.RadioButton
            )
            .semantics {
                contentDescription = label
            },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(45.dp)
                .clip(CircleShape)
                .background(color.asColor())
                .border(
                    width = if (isSelected) 2.dp else 1.dp,
                    color = if (isSelected) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.outline
                    },
                    shape = CircleShape
                )
        ) {
            if (isSelected) {
                // Selected color indicator
                Icon(
                    painter = painterResource(R.drawable.core_ui_check),
                    contentDescription = null, // Already described by parent semantics
                    modifier = Modifier
                        .size(35.dp)
                        .align(Alignment.Center)
                )
            }
        }
    }
}