package com.mobilez.domain.usecases.form

import com.mobilez.domain.usecases.form.validators.NumberInputType
import com.mobilez.domain.usecases.form.validators.NumberInputValidator
import com.mobilez.domain.usecases.form.validators.WordInputValidator
import com.mobilez.fizzbuzz.domain.models.form.QueryForm
import com.mobilez.fizzbuzz.domain.models.form.QueryFormValidation
import javax.inject.Inject

class ValidateFizzBuzzFormUseCase @Inject constructor() {

    operator fun invoke(queryForm: QueryForm): QueryFormValidation {
        val firstNumberValidation = NumberInputValidator.validate(
            queryForm.firstNumber,
            NumberInputType.FIRST_NUMBER,
        )

        val secondNumberValidation = NumberInputValidator.validate(
            queryForm.secondNumber,
            NumberInputType.SECOND_NUMBER,
        )

        val limitValidation = NumberInputValidator.validate(
            queryForm.limit,
            NumberInputType.LIMIT,
        )

        val wordsValidation = WordInputValidator.validate(
            queryForm,
        )
        return firstNumberValidation + secondNumberValidation + limitValidation + wordsValidation
    }
}
