package com.mobilez.feature.form

import androidx.lifecycle.ViewModel
import com.mobilez.domain.usecases.form.ValidateFizzBuzzFormUseCase
import com.mobilez.feature.form.FormUiModel.Companion.toQueryForm
import com.mobilez.fizzbuzz.domain.models.form.QueryFormValidation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class FormScreenViewModel @Inject constructor(
    private val validateFizzBuzzFormUseCase: ValidateFizzBuzzFormUseCase,
) : ViewModel() {

    private val _formUiModel = MutableStateFlow(
        FormUiModel(
            isValid = true,
            input1 = "3",
            input2 = "5",
            word1 = "Fizz",
            word2 = "Buzz",
            limit = "100",
        ),
    )
    val formUiModel = _formUiModel.asStateFlow()

    fun updateFormData(updatedData: FormUiModel) {
        _formUiModel.value = updatedData
        val validateInputs = validateInputs() // validate the form whenever it changes

        _formUiModel.value = updatedData.copy(
            isValid = validateInputs.isValid,
            errors = FormUiModel.FormErrors(
                input1Error = validateInputs.error?.firstNumberError ?: "",
                input2Error = validateInputs.error?.secondNumberError ?: "",
                word1Error = validateInputs.error?.firstWordError ?: "",
                word2Error = validateInputs.error?.secondWordError ?: "",
                limitError = validateInputs.error?.limitError ?: "",
            ),
        )
    }

    private fun validateInputs(): QueryFormValidation =
        validateFizzBuzzFormUseCase(formUiModel.value.toQueryForm())
}
