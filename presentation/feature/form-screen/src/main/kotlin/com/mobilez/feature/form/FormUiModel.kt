package com.mobilez.feature.form

import com.mobilez.fizzbuzz.domain.models.Query
import com.mobilez.fizzbuzz.domain.models.form.QueryForm

data class FormUiModel(
    val isValid: Boolean = false,
    val input1: String = "",
    val input2: String = "",
    val word1: String = "",
    val word2: String = "",
    val limit: String = "",
    val errors: FormErrors = FormErrors(),
) {
    data class FormErrors(
        val input1Error: String = "",
        val input2Error: String = "",
        val word1Error: String = "",
        val word2Error: String = "",
        val limitError: String = "",
    )

    companion object {
        fun FormUiModel.toQueryForm(): QueryForm = QueryForm(
            firstNumber = input1,
            secondNumber = input2,
            limit = limit,
            firstWord = word1,
            secondWord = word2,
        )

        fun FormUiModel.toQuery(): Query = Query(
            firstNumber = input1.toInt(),
            secondNumber = input2.toInt(),
            limit = limit.toInt(),
            firstWord = word1,
            secondWord = word2,
        )
    }
}
