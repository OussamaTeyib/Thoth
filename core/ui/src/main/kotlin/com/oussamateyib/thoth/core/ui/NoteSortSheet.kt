package com.oussamateyib.thoth.core.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.oussamateyib.thoth.core.domain.util.NoteOrder
import com.oussamateyib.thoth.core.domain.util.OrderType

@Composable
fun NoteSortSheet(
    onOrderChange: (NoteOrder) -> Unit,
    modifier: Modifier = Modifier,
    noteOrder: NoteOrder = NoteOrder.DateUpdated(OrderType.Descending)
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.core_ui_sort_by),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)
        )
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))
        SortOptionRow(
            label = stringResource(R.string.core_ui_date_created),
            selected = noteOrder is NoteOrder.DateCreated,
            orderType = noteOrder.orderType,
            onClick = {
                onOrderChange(
                    if (noteOrder is NoteOrder.DateCreated) {
                        noteOrder.copy(noteOrder.orderType.toggle())
                    } else {
                        NoteOrder.DateCreated(noteOrder.orderType)
                    }
                )
            }
        )
        SortOptionRow(
            label = stringResource(R.string.core_ui_date_modified),
            selected = noteOrder is NoteOrder.DateUpdated,
            orderType = noteOrder.orderType,
            onClick = {
                onOrderChange(
                    if (noteOrder is NoteOrder.DateUpdated) {
                        noteOrder.copy(noteOrder.orderType.toggle())
                    } else {
                        NoteOrder.DateUpdated(noteOrder.orderType)
                    }
                )
            }
        )
        SortOptionRow(
            label = stringResource(R.string.core_ui_title),
            selected = noteOrder is NoteOrder.Title,
            orderType = noteOrder.orderType,
            onClick = {
                onOrderChange(
                    if (noteOrder is NoteOrder.Title) {
                        noteOrder.copy(noteOrder.orderType.toggle())
                    } else {
                        NoteOrder.Title(noteOrder.orderType)
                    }
                )
            }
        )
    }
}

@Composable
internal fun SortOptionRow(
    label: String,
    selected: Boolean,
    orderType: OrderType,
    onClick: () -> Unit
) {
    ListItem(
        headlineContent = { Text(label) },
        leadingContent = {
            Box(
                modifier = Modifier.size(24.dp),
                contentAlignment = Alignment.Center
            ) {
                if (selected) {
                    val isAscending = orderType is OrderType.Ascending
                    Icon(
                        painter = painterResource(if (isAscending) R.drawable.core_ui_arrow_upward else R.drawable.core_ui_arrow_downward),
                        contentDescription = stringResource(if (isAscending) R.string.core_ui_ascending else R.string.core_ui_descending)
                    )
                }
            }
        },
        colors = ListItemDefaults.colors(
            containerColor = if (selected) MaterialTheme.colorScheme.primaryContainer else Color.Transparent,
            headlineColor = if (selected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurface
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp)
            .clip(CircleShape)
            .clickable(onClick = onClick)
    )
}