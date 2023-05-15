package com.mobilez.fizzbuzz.domain.models

import com.google.common.truth.Truth.assertThat
import com.mobilez.fizzbuzz.domain.models.form.QueryFormValidationError
import org.junit.Test

class QueryFormValidationErrorTest {

    @Test
    fun `plus operator combines errors correctly`() {
        // Arrange
        val error1 = QueryFormValidationError(
            firstNumberError = QueryFormValidationError.NEGATIVE_NUMBER_INPUT_ERROR,
            secondNumberError = QueryFormValidationError.NEGATIVE_NUMBER_INPUT_ERROR,
            firstWordError = QueryFormValidationError.WORD_INPUT_ERROR,
            secondWordError = QueryFormValidationError.WORD_INPUT_ERROR,
            limitError = QueryFormValidationError.NEGATIVE_LIMIT_INPUT_ERROR,
        )

        val error2 = QueryFormValidationError(
            firstNumberError = QueryFormValidationError.SAME_NUMBER_ERROR,
            secondNumberError = QueryFormValidationError.SAME_NUMBER_ERROR,
            firstWordError = QueryFormValidationError.SAME_WORD_ERROR,
            secondWordError = QueryFormValidationError.SAME_WORD_ERROR,
            limitError = QueryFormValidationError.NEGATIVE_LIMIT_INPUT_ERROR,
        )

        // Act
        val combinedError = error1 + error2

        // Assert
        assertThat(combinedError.firstNumberError).isEqualTo(QueryFormValidationError.NEGATIVE_NUMBER_INPUT_ERROR + "\n" + QueryFormValidationError.SAME_NUMBER_ERROR)
        assertThat(combinedError.secondNumberError).isEqualTo(QueryFormValidationError.NEGATIVE_NUMBER_INPUT_ERROR + "\n" + QueryFormValidationError.SAME_NUMBER_ERROR)
        assertThat(combinedError.firstWordError).isEqualTo(QueryFormValidationError.WORD_INPUT_ERROR + "\n" + QueryFormValidationError.SAME_WORD_ERROR)
        assertThat(combinedError.secondWordError).isEqualTo(QueryFormValidationError.WORD_INPUT_ERROR + "\n" + QueryFormValidationError.SAME_WORD_ERROR)
        assertThat(combinedError.limitError).isEqualTo(QueryFormValidationError.NEGATIVE_LIMIT_INPUT_ERROR + "\n" + QueryFormValidationError.NEGATIVE_LIMIT_INPUT_ERROR)
    }
}
