package com.oussamateyib.thoth.core.designsystem.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun TransparentTextField(
    value: TextFieldValue,
    hint: String,
    onValueChange: (TextFieldValue) -> Unit,
    onFocusChange: (FocusState) -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    isHintVisible: Boolean = true,
    singleLine: Boolean = false
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = singleLine,
        textStyle = textStyle.copy(color = LocalContentColor.current),
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
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