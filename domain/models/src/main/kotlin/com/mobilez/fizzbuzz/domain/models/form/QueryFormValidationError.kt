package com.mobilez.fizzbuzz.domain.models.form

data class QueryFormValidationError(
    val firstNumberError: String = "",
    val secondNumberError: String = "",
    val firstWordError: String = "",
    val secondWordError: String = "",
    val limitError: String = "",
) {
    companion object {
        const val EMPTY_NUMBER_ERROR = "Number input should not be empty."
        const val NEGATIVE_NUMBER_INPUT_ERROR = "Number Input should not be less than 1."
        const val NUMBER_INPUT_ERROR = "Must contain only digits, no symbols, no whitespaces, no alphabetic."
        const val SAME_NUMBER_ERROR = "Numbers should not be the same."
        const val NEGATIVE_LIMIT_INPUT_ERROR = "Limit Input should not be less than 1."
        const val WORD_INPUT_ERROR = "Must contain only letters, no symbols no whitespaces."
        const val EMPTY_WORD_ERROR = "Word input should not be empty."
        const val SAME_WORD_ERROR = "Words should not be the same."
    }

    internal infix operator fun plus(other: QueryFormValidationError): QueryFormValidationError =
        QueryFormValidationError(
            firstNumberError = firstNumberError.concatenateIfNotEmpty(other.firstNumberError),
            secondNumberError = secondNumberError.concatenateIfNotEmpty(other.secondNumberError),
            firstWordError = firstWordError.concatenateIfNotEmpty(other.firstWordError),
            secondWordError = secondWordError.concatenateIfNotEmpty(other.secondWordError),
            limitError = limitError.concatenateIfNotEmpty(other.limitError),
        )

    private fun String.concatenateIfNotEmpty(other: String): String = (if (other.isNotEmpty()) "$this\n$other" else this).trim()
}
