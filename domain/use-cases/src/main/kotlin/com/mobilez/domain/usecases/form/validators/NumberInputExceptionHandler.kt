package com.mobilez.domain.usecases.form.validators

import com.mobilez.fizzbuzz.domain.models.form.QueryFormValidation

internal interface NumberInputExceptionHandler {
    fun handleException(e: Exception): QueryFormValidation
}
