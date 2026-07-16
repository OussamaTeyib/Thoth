package com.oussamateyib.thoth.core.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun SettingsDialog(
    titleRes: Int,
    onDismiss: () -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) = AlertDialog(
    onDismissRequest = onDismiss,
    modifier = Modifier.widthIn(max = 320.dp),
    title = {
        Text(
            text = stringResource(titleRes),
            style = MaterialTheme.typography.titleLarge,
        )
    },
    text = {
        Column(
            Modifier.selectableGroup(),
        ) {
            HorizontalDivider()
            Column(
                modifier = Modifier
                    .heightIn(max = 240.dp)
                    .verticalScroll(rememberScrollState()),
                content = content,
            )
            HorizontalDivider()
        }
    },
    confirmButton = {
        TextButton(onClick = onDismiss) {
            Text(text = stringResource(R.string.core_ui_dismiss_dialog_button_text))
        }
    },
)
