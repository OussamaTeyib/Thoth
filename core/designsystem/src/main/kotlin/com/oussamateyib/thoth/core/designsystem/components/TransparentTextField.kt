package com.oussamateyib.thoth.core.designsystem.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.TextStyle

@Composable
fun TransparentTextField(
    value: String,
    hint: String,
    onValueChange: (String) -> Unit,
    onFocusChange: (FocusState) -> Unit,
    textStyle: TextStyle,
    modifier: Modifier = Modifier,
    isHintVisible: Boolean = true,
    singleLine: Boolean = false
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = singleLine,
        textStyle = textStyle.copy(color = LocalContentColor.current),
        modifier = modifier
            .onFocusChanged {
                onFocusChange(it)
            },
        decorationBox = { innerTextField ->
            Box {
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