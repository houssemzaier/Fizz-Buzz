package com.mobilez.design.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun FizzBuzzFormTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isError: Boolean,
    errorText: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    focusRequester: FocusRequester,
    imeAction: ImeAction = ImeAction.Next,
    onImeActionDonePerformed: () -> Unit = {},
    onImeActionNextPerformed: () -> Unit = {},
) {
    var textFieldState by remember { mutableStateOf(TextFieldValue(value)) }

    TextField(
        value = textFieldState,
        onValueChange = {
            textFieldState = it
            onValueChange(it.text)
        },
        label = { Text(label) },
        isError = isError,
        modifier = Modifier
            .focusRequester(focusRequester)
            .onFocusChanged { state ->
                if (state.isFocused) {
                    textFieldState = textFieldState.copy(
                        selection = TextRange(textFieldState.text.length)
                    )
                }
            },
        supportingText = {
            if (isError) {
                Text(
                    text = errorText,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.error,
                )
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = KeyboardActions(
            onNext = { onImeActionNextPerformed() },
            onDone = { onImeActionDonePerformed() },
        ),
    )
}
