package com.oussamateyib.thoth.core.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController

@Composable
fun ColorPicker(
    selectedColor: Int,
    onColorChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val controller = rememberColorPickerController()

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        HsvColorPicker(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            controller = controller,
            initialColor = Color(selectedColor),
            onColorChanged = { envelope ->
                if (envelope.fromUser) {
                    onColorChange(envelope.color.toArgb())
                }
            }
        )
    }
}