package com.oussamateyib.thoth.core.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDirection

@Composable
fun TransparentTextField(
    value: TextFieldValue,
    hint: String,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    isHintVisible: Boolean = true
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = textStyle.copy(
            color = LocalContentColor.current,
            textDirection = TextDirection.Content
        ),
        cursorBrush = SolidColor(LocalContentColor.current),
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        modifier = modifier,
        decorationBox = { innerTextField ->
            Box(
                // Ensure children occupy the full width
                propagateMinConstraints = true
            ) {
                if (isHintVisible) {
                    Text(
                        text = hint,
                        style = textStyle,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                // Display the actual text field
                innerTextField()
            }
        }
    )
}