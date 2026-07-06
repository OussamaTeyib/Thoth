package com.oussamateyib.thoth.core.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun SettingsSectionTitle(
    text: String,
    modifier: Modifier = Modifier
) = Text(
    text = text,
    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
    modifier = modifier.padding(start = 20.dp, top = 10.dp, end = 20.dp, bottom = 8.dp)
)