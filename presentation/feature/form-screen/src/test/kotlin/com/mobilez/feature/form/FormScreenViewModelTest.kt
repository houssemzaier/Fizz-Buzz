package com.mobilez.feature.form

import com.google.common.truth.Truth.assertThat
import com.mobilez.domain.usecases.form.ValidateFizzBuzzFormUseCase
import com.mobilez.feature.form.FormUiModel.Companion.toQueryForm
import com.mobilez.fizzbuzz.domain.models.form.QueryFormValidation
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class FormScreenViewModelTest {

    @Mock
    private lateinit var validateFizzBuzzFormUseCase: ValidateFizzBuzzFormUseCase

    private lateinit var formScreenViewModel: FormScreenViewModel

    @Before
    fun setup() {
        formScreenViewModel = FormScreenViewModel(validateFizzBuzzFormUseCase)
    }

    @Test
    fun `when updateFormData is called, validateInputs should be invoked`() = runTest {
        //Arrange
        val updatedData = FormUiModel(
            isValid = false,
            input1 = "3",
            input2 = "5",
            word1 = "Fizz",
            word2 = "Buzz",
            limit = "100",
        )

        val validation = QueryFormValidation(
            isValid = true,
        )

        whenever(validateFizzBuzzFormUseCase(updatedData.toQueryForm())).thenReturn(validation)

        //Act
        formScreenViewModel.updateFormData(updatedData)

        //Assert
        val formUiModel = formScreenViewModel.formUiModel.first()

        assertThat(formUiModel.isValid).isEqualTo(true)
        assertThat(formUiModel.errors.input1Error).isEmpty()
        assertThat(formUiModel.errors.input2Error).isEmpty()
        assertThat(formUiModel.errors.word1Error).isEmpty()
        assertThat(formUiModel.errors.word2Error).isEmpty()
        assertThat(formUiModel.errors.limitError).isEmpty()
    }
}
