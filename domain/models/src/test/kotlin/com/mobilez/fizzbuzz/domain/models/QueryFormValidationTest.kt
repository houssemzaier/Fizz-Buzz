package com.mobilez.fizzbuzz.domain.models

import com.google.common.truth.Truth.assertThat
import com.mobilez.fizzbuzz.domain.models.form.QueryFormValidation
import com.mobilez.fizzbuzz.domain.models.form.QueryFormValidationError
import org.junit.Test

class QueryFormValidationTest {

    @Test
    fun `plus operator combines validation results correctly`() {
        // Arrange
        val validation1 = QueryFormValidation(
            isValid = false,
            error = QueryFormValidationError(
                firstNumberError = QueryFormValidationError.NEGATIVE_NUMBER_INPUT_ERROR,
                secondNumberError = QueryFormValidationError.NEGATIVE_NUMBER_INPUT_ERROR,
                firstWordError = QueryFormValidationError.WORD_INPUT_ERROR,
                secondWordError = QueryFormValidationError.WORD_INPUT_ERROR,
                limitError = QueryFormValidationError.NEGATIVE_LIMIT_INPUT_ERROR,
            ),
        )

        val validation2 = QueryFormValidation(
            isValid = false,
            error = QueryFormValidationError(
                firstNumberError = QueryFormValidationError.SAME_NUMBER_ERROR,
                secondNumberError = QueryFormValidationError.SAME_NUMBER_ERROR,
                firstWordError = QueryFormValidationError.SAME_WORD_ERROR,
                secondWordError = QueryFormValidationError.SAME_WORD_ERROR,
                limitError = QueryFormValidationError.NEGATIVE_LIMIT_INPUT_ERROR,
            ),
        )

        // Act
        val combinedValidation = validation1 + validation2

        // Assert
        assertThat(combinedValidation.isValid).isFalse()
        assertThat(combinedValidation.error?.firstNumberError).isEqualTo(QueryFormValidationError.NEGATIVE_NUMBER_INPUT_ERROR + "\n" + QueryFormValidationError.SAME_NUMBER_ERROR)
        assertThat(combinedValidation.error?.secondNumberError).isEqualTo(QueryFormValidationError.NEGATIVE_NUMBER_INPUT_ERROR + "\n" + QueryFormValidationError.SAME_NUMBER_ERROR)
        assertThat(combinedValidation.error?.firstWordError).isEqualTo(QueryFormValidationError.WORD_INPUT_ERROR + "\n" + QueryFormValidationError.SAME_WORD_ERROR)
        assertThat(combinedValidation.error?.secondWordError).isEqualTo(QueryFormValidationError.WORD_INPUT_ERROR + "\n" + QueryFormValidationError.SAME_WORD_ERROR)
        assertThat(combinedValidation.error?.limitError).isEqualTo(QueryFormValidationError.NEGATIVE_LIMIT_INPUT_ERROR + "\n" + QueryFormValidationError.NEGATIVE_LIMIT_INPUT_ERROR)
    }
}
