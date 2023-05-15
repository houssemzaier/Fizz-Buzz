package com.mobilez.fizzbuzz.domain.models.form

data class QueryFormValidation(
    val isValid: Boolean,
    val error: QueryFormValidationError? = null,
) {
    infix operator fun plus(other: QueryFormValidation): QueryFormValidation {
        // Combine the isValid fields
        val combinedIsValid = this.isValid && other.isValid

        // Combine the error fields
        val combinedError = when {
            // If both errors are non-null, combine them using the plus operator
            this.error != null && other.error != null -> this.error.plus(other.error)

            // If this error is non-null and other error is null, use this error
            this.error != null && other.error == null -> this.error

            // If this error is null and other error is non-null, use other error
            else -> other.error
        }

        return QueryFormValidation(
            isValid = combinedIsValid,
            error = combinedError,
        )
    }
}
