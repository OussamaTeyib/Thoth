package com.oussamateyib.thoth.core.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDirection

@Composable
fun TransparentTextField(
    state: TextFieldState,
    hint: String,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle.Default,
    onKeyboardAction: KeyboardActionHandler? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    isHintVisible: Boolean = true,
) = BasicTextField(
    state = state,
    textStyle = textStyle.copy(
        color = LocalContentColor.current,
        textDirection = TextDirection.Content,
    ),
    cursorBrush = SolidColor(LocalContentColor.current),
    onKeyboardAction = onKeyboardAction,
    keyboardOptions = keyboardOptions,
    modifier = modifier,
    decorator = { innerTextField ->
        Box(
            // Ensure children occupy the full width
            propagateMinConstraints = true,
        ) {
            if (isHintVisible) {
                Text(
                    text = hint,
                    style = textStyle,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            // Display the actual text field
            innerTextField()
        }
    },
)
