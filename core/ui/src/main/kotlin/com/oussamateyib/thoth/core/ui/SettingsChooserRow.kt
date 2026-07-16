package com.oussamateyib.thoth.core.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp

@Composable
fun SettingsChooserRow(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) = Row(
    Modifier
        .fillMaxWidth()
        .selectable(
            selected = selected,
            role = Role.RadioButton,
            onClick = onClick
        )
        .padding(vertical = 16.dp),
    verticalAlignment = Alignment.CenterVertically
) {
    RadioButton(
        selected = selected,
        onClick = null
    )
    Spacer(Modifier.width(16.dp))
    Text(
        text = text,
        style = MaterialTheme.typography.bodyLarge
    )
}
