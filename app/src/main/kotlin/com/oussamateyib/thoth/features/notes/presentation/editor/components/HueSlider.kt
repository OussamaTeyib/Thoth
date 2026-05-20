package com.oussamateyib.thoth.features.notes.presentation.editor.components

import android.graphics.Color.colorToHSV
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp

@Composable
fun HueSlider(
    selectedColor: Int,
    onColorChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val hueColors = remember {
        (0..360 step 30).map { hue ->
            Color.hsv(hue.toFloat(), 1f, 1f)
        }
    }

    val currentHue = remember(selectedColor) {
        FloatArray(3).apply {
            colorToHSV(selectedColor, this)
        }[0]
    }

    var sliderWidth by remember { mutableFloatStateOf(0f) }

    val thumbRadius = 14.dp

    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(32.dp)
            .pointerInput(sliderWidth) {
                detectHorizontalDragGestures { change, _ ->
                    val x = change.position.x.coerceIn(0f, sliderWidth)
                    onColorChange(Color.hsv((x / sliderWidth) * 360f, 1f, 1f).toArgb())
                }
            }
            .pointerInput(sliderWidth) {
                detectTapGestures { offset ->
                    val x = offset.x.coerceIn(0f, sliderWidth)
                    onColorChange(Color.hsv((x / sliderWidth) * 360f, 1f, 1f).toArgb())
                }
            }
    ) {
        sliderWidth = size.width

        val trackHeight = 12.dp.toPx()
        val trackY = center.y - trackHeight / 2

        drawRoundRect(
            brush = Brush.horizontalGradient(hueColors),
            topLeft = Offset(0f, trackY),
            size = Size(size.width, trackHeight),
            cornerRadius = CornerRadius(trackHeight / 2)
        )

        val thumbX = (currentHue / 360f) * sliderWidth
        drawCircle(
            color = Color.hsv(currentHue, 1f, 1f),
            radius = thumbRadius.toPx(),
            center = Offset(thumbX, center.y)
        )
        drawCircle(
            color = Color.White,
            radius = thumbRadius.toPx(),
            center = Offset(thumbX, center.y),
            style = Stroke(width = 3.dp.toPx())
        )
    }
}