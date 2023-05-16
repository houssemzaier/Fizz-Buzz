package com.mobilez.feature.form


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mobilez.design.components.FizzBuzzFormTextField
import com.mobilez.design.theme.FizzBuzzTheme
import com.mobilez.feature.form.FormUiModel.Companion.toQuery
import com.mobilez.fizzbuzz.domain.models.Query

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FizzBuzzForm(
    formStateUiModel: FormUiModel,
    onValueChanged: (FormUiModel) -> Unit,
    onSubmitClick: (Query) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    val input1FocusRequester = remember { FocusRequester() }
    val input2FocusRequester = remember { FocusRequester() }
    val word1FocusRequester = remember { FocusRequester() }
    val word2FocusRequester = remember { FocusRequester() }
    val limitFocusRequester = remember { FocusRequester() }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier.padding(vertical = 10.dp),
            text = "FizzBuzz",
            style = MaterialTheme.typography.titleLarge,
        )

        FizzBuzzFormTextField(
            value = formStateUiModel.input1,
            onValueChange = { onValueChanged(formStateUiModel.copy(input1 = it)) },
            label = "Input 1",
            isError = formStateUiModel.errors.input1Error.isNotEmpty(),
            errorText = formStateUiModel.errors.input1Error,
            keyboardType = KeyboardType.Number,
            focusRequester = input1FocusRequester,
            onImeActionNextPerformed = { input2FocusRequester.requestFocus() },
        )

        FizzBuzzFormTextField(
            value = formStateUiModel.input2,
            onValueChange = { onValueChanged(formStateUiModel.copy(input2 = it)) },
            label = "Input 2",
            isError = formStateUiModel.errors.input2Error.isNotEmpty(),
            errorText = formStateUiModel.errors.input2Error,
            keyboardType = KeyboardType.Number,
            focusRequester = input2FocusRequester,
            onImeActionNextPerformed = { word1FocusRequester.requestFocus() },
        )

        FizzBuzzFormTextField(
            value = formStateUiModel.word1,
            onValueChange = { onValueChanged(formStateUiModel.copy(word1 = it)) },
            label = "Word 1",
            isError = formStateUiModel.errors.word1Error.isNotEmpty(),
            errorText = formStateUiModel.errors.word1Error,
            focusRequester = word1FocusRequester,
            onImeActionNextPerformed = { word2FocusRequester.requestFocus() },
        )

        FizzBuzzFormTextField(
            value = formStateUiModel.word2,
            onValueChange = { onValueChanged(formStateUiModel.copy(word2 = it)) },
            label = "Word 2",
            isError = formStateUiModel.errors.word2Error.isNotEmpty(),
            errorText = formStateUiModel.errors.word2Error,
            focusRequester = word2FocusRequester,
            onImeActionNextPerformed = { limitFocusRequester.requestFocus() },
        )

        FizzBuzzFormTextField(
            value = formStateUiModel.limit,
            onValueChange = { onValueChanged(formStateUiModel.copy(limit = it)) },
            label = "Limit",
            isError = formStateUiModel.errors.limitError.isNotEmpty(),
            errorText = formStateUiModel.errors.limitError,
            keyboardType = KeyboardType.Number,
            focusRequester = limitFocusRequester,
            imeAction = ImeAction.Done,
            onImeActionDonePerformed = {
                keyboardController?.hide() ?: if (formStateUiModel.isValid) onSubmitClick(formStateUiModel.toQuery()) else Unit
            },
        )

        Button(
            onClick = { onSubmitWhenFormStateIsValid(onSubmitClick, formStateUiModel) },
            modifier = Modifier
                .align(Alignment.End)
                .padding(
                    end = 10.dp,
                    bottom = 10.dp,
                ),
            enabled = formStateUiModel.isValid,
        ) {
            Text("Submit")
        }
    }
    DisposableEffect(Unit) {
        input1FocusRequester.requestFocus()
        onDispose { }
    }
}

private fun onSubmitWhenFormStateIsValid(
    onSubmitClick: (Query) -> Unit,
    formStateUiModel: FormUiModel,
) {
    onSubmitClick(formStateUiModel.toQuery())
}

@Preview
@Composable
private fun FizzBuzzFormPrev() {
    FizzBuzzTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            FizzBuzzForm(FormUiModel(), onValueChanged = {}, onSubmitClick = {})
        }
    }
}
