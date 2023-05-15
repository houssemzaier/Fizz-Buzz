package com.mobilez.domain.usecases.form.validators

import com.mobilez.fizzbuzz.domain.models.form.QueryFormValidation
import com.mobilez.fizzbuzz.domain.models.form.QueryFormValidationError

internal enum class NumberInputType : NumberInputExceptionHandler {
    FIRST_NUMBER {
        override fun handleException(e: Exception): QueryFormValidation {
            return when (e) {
                is NumberFormatException -> QueryFormValidation(
                    isValid = false,
                    error = QueryFormValidationError(firstNumberError = QueryFormValidationError.NUMBER_INPUT_ERROR),
                )

                is NegativeOrZeroNumberInputException -> QueryFormValidation(
                    isValid = false,
                    error = QueryFormValidationError(firstNumberError = QueryFormValidationError.NEGATIVE_NUMBER_INPUT_ERROR),
                )

                is EmptyInputException -> QueryFormValidation(
                    isValid = false,
                    error = QueryFormValidationError(firstNumberError = QueryFormValidationError.EMPTY_NUMBER_ERROR),
                )

                else -> throw e
            }

        }
    },
    SECOND_NUMBER {
        override fun handleException(e: Exception): QueryFormValidation {
            return when (e) {
                is NumberFormatException -> QueryFormValidation(
                    isValid = false,
                    error = QueryFormValidationError(secondNumberError = QueryFormValidationError.NUMBER_INPUT_ERROR),
                )

                is NegativeOrZeroNumberInputException -> QueryFormValidation(
                    isValid = false,
                    error = QueryFormValidationError(secondNumberError = QueryFormValidationError.NEGATIVE_NUMBER_INPUT_ERROR),
                )

                is EmptyInputException -> QueryFormValidation(
                    isValid = false,
                    error = QueryFormValidationError(secondNumberError = QueryFormValidationError.EMPTY_NUMBER_ERROR),
                )

                else -> throw e
            }
        }
    },
    LIMIT {
        override fun handleException(e: Exception): QueryFormValidation {
            return when (e) {
                is NumberFormatException -> QueryFormValidation(
                    isValid = false,
                    error = QueryFormValidationError(limitError = QueryFormValidationError.NUMBER_INPUT_ERROR),
                )

                is NegativeOrZeroNumberInputException -> QueryFormValidation(
                    isValid = false,
                    error = QueryFormValidationError(limitError = QueryFormValidationError.NEGATIVE_LIMIT_INPUT_ERROR),
                )

                is EmptyInputException -> QueryFormValidation(
                    isValid = false,
                    error = QueryFormValidationError(limitError = QueryFormValidationError.EMPTY_NUMBER_ERROR),
                )

                else -> throw e
            }
        }
    },
}


