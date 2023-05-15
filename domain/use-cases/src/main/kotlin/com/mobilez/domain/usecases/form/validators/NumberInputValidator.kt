package com.mobilez.domain.usecases.form.validators

import com.mobilez.fizzbuzz.domain.models.form.QueryFormValidation

internal object NumberInputValidator {
    fun validate(
        number: String,
        numberInputType: NumberInputType,
    ): QueryFormValidation {
        return try {
            validateNumberInputNotEmpty(number) +
                validateNumberInputNotNegativeOrZero(number) +
                validateNumberInputOnlyDigits(number)
        } catch (e: Exception) {
            numberInputType.handleException(e)
        }
    }

    private fun validateNumberInputNotNegativeOrZero(limit: String): QueryFormValidation {
        if (limit.toInt() <= 0) throw NegativeOrZeroNumberInputException
        return QueryFormValidation(isValid = true)
    }

    private fun validateNumberInputNotEmpty(limitNumber: String): QueryFormValidation {
        if (limitNumber.isBlank()) throw EmptyInputException
        return QueryFormValidation(isValid = true)
    }

    private fun validateNumberInputOnlyDigits(limit: String): QueryFormValidation {
        if (limit.matches(Regex("^[0-9]*$")).not()) throw NumberFormatException()
        return QueryFormValidation(isValid = true)
    }
}
