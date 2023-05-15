package com.mobilez.domain.usecases.form.validators

import com.mobilez.fizzbuzz.domain.models.form.QueryForm
import com.mobilez.fizzbuzz.domain.models.form.QueryFormValidation
import com.mobilez.fizzbuzz.domain.models.form.QueryFormValidationError

internal object WordInputValidator {
    fun validate(
        queryForm: QueryForm,
    ): QueryFormValidation {
        return validateNotSameNumber(queryForm.firstNumber, queryForm.secondNumber) +
            validateFirstWordInputNotEmpty(queryForm.firstWord) +
            validateFirstWordInputShouldNotContainDigits(queryForm.firstWord) +
            validateSecondWordInputNotEmpty(queryForm.secondWord) +
            validateSecondWordInputShouldNotContainDigits(queryForm.secondWord) +
            validateNotSameWord(queryForm.firstWord, queryForm.secondWord)
    }


    private fun validateFirstWordInputShouldNotContainDigits(word: String): QueryFormValidation =
        validateInput(
            QueryFormValidationError(firstWordError = QueryFormValidationError.WORD_INPUT_ERROR),
            errorCondition = { word.matches(Regex("^[a-zA-Z]*$")).not() },
        )

    private fun validateSecondWordInputShouldNotContainDigits(word: String): QueryFormValidation =
        validateInput(
            QueryFormValidationError(secondWordError = QueryFormValidationError.WORD_INPUT_ERROR),
            errorCondition = { word.matches(Regex("^[a-zA-Z]*$")).not() },
        )

    private fun validateFirstWordInputNotEmpty(firstWord: String): QueryFormValidation =
        validateInput(
            QueryFormValidationError(firstWordError = QueryFormValidationError.EMPTY_WORD_ERROR),
            errorCondition = { firstWord.isBlank() },
        )

    private fun validateSecondWordInputNotEmpty(secondWord: String): QueryFormValidation =
        validateInput(
            QueryFormValidationError(secondWordError = QueryFormValidationError.EMPTY_WORD_ERROR),
            errorCondition = { secondWord.isBlank() },
        )


    private fun validateNotSameWord(firstWord: String, secondWord: String): QueryFormValidation =
        validateInput(
            QueryFormValidationError(
                firstWordError = QueryFormValidationError.SAME_WORD_ERROR,
                secondWordError = QueryFormValidationError.SAME_WORD_ERROR,
            ),
            errorCondition = { firstWord == secondWord && firstWord.isNotBlank() && secondWord.isNotBlank() },
        )

    private fun validateNotSameNumber(firstNumber: String, secondNumber: String): QueryFormValidation =
        validateInput(
            QueryFormValidationError(
                firstNumberError = QueryFormValidationError.SAME_NUMBER_ERROR,
                secondNumberError = QueryFormValidationError.SAME_NUMBER_ERROR,
            ),
            errorCondition = { firstNumber == secondNumber && firstNumber.isNotBlank() && secondNumber.isNotBlank() },
        )

    private fun validateInput(
        queryFormValidationError: QueryFormValidationError,
        errorCondition: () -> Boolean,
    ): QueryFormValidation = if (errorCondition()) {
        QueryFormValidation(isValid = false, error = queryFormValidationError)
    } else {
        QueryFormValidation(isValid = true)
    }
}
