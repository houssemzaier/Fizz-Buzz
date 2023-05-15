package com.mobilez.feature.form

import androidx.compose.runtime.Composable
import com.mobilez.fizzbuzz.domain.models.Query

@Composable
fun FormScreen(
    formUiModel: FormUiModel,
    onValueChange: (FormUiModel) -> Unit,
    onSubmitClick: (Query) -> Unit,
) {
    FizzBuzzForm(formUiModel, onValueChange, onSubmitClick)
}

