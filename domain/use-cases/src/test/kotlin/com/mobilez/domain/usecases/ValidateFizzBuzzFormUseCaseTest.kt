package com.mobilez.domain.usecases

import com.google.common.truth.Truth.assertThat
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameter.TestParameterValuesProvider
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import com.mobilez.domain.usecases.form.ValidateFizzBuzzFormUseCase
import com.mobilez.fizzbuzz.domain.models.form.QueryForm
import com.mobilez.fizzbuzz.domain.models.form.QueryFormValidation
import com.mobilez.fizzbuzz.domain.models.form.QueryFormValidationError
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class ValidateFizzBuzzFormUseCaseTest {

    private val validateFizzBuzzFormUseCase = ValidateFizzBuzzFormUseCase()

    @Test
    fun validate(
        @TestParameter(valuesProvider = QueryAndValidationProvider::class) pair: Pair<QueryForm, QueryFormValidation>,
    ) {
        // Arrange
        val (queryFrom, expected) = pair

        // Act
        val validation = validateFizzBuzzFormUseCase(queryFrom)

        // Assert
        assertThat(validation.isValid).isEqualTo(expected.isValid)
        assertThat(validation.error?.firstNumberError).isEqualTo(expected.error?.firstNumberError)
        assertThat(validation.error?.secondNumberError).isEqualTo(expected.error?.secondNumberError)
        assertThat(validation.error?.firstWordError).isEqualTo(expected.error?.firstWordError)
        assertThat(validation.error?.secondWordError).isEqualTo(expected.error?.secondWordError)
        assertThat(validation.error?.limitError).isEqualTo(expected.error?.limitError)
    }

    private class QueryAndValidationProvider : TestParameterValuesProvider {
        override fun provideValues(): List<Pair<QueryForm, QueryFormValidation>> = listOf(
            Pair(
                QueryForm("0", "5", "100", "Fizz", "Buzz"),
                QueryFormValidation(false, QueryFormValidationError(firstNumberError = QueryFormValidationError.NEGATIVE_NUMBER_INPUT_ERROR)),
            ),
            Pair(
                QueryForm("3", "5", "100", "Fizz", "Buzz1"),
                QueryFormValidation(false, QueryFormValidationError(secondWordError = QueryFormValidationError.WORD_INPUT_ERROR)),
            ),
            Pair(
                QueryForm("3", "0", "100", "Fizz", "Buzz"),
                QueryFormValidation(false, QueryFormValidationError(secondNumberError = QueryFormValidationError.NEGATIVE_NUMBER_INPUT_ERROR)),
            ),
            Pair(
                QueryForm("3", "5", "0", "Fizz", "Buzz"),
                QueryFormValidation(false, QueryFormValidationError(limitError = QueryFormValidationError.NEGATIVE_LIMIT_INPUT_ERROR)),
            ),
            Pair(
                QueryForm("3", "3", "100", "Fizz", "Buzz"),
                QueryFormValidation(false, QueryFormValidationError(firstNumberError = QueryFormValidationError.SAME_NUMBER_ERROR, secondNumberError = QueryFormValidationError.SAME_NUMBER_ERROR)),
            ),
            Pair(
                QueryForm("3", "5", "100", "Fizz", "Fizz"),
                QueryFormValidation(false, QueryFormValidationError(firstWordError = QueryFormValidationError.SAME_WORD_ERROR, secondWordError = QueryFormValidationError.SAME_WORD_ERROR)),
            ),
            Pair(
                QueryForm("3", "5", "100", "Fizz1", "Buzz"),
                QueryFormValidation(false, QueryFormValidationError(firstWordError = QueryFormValidationError.WORD_INPUT_ERROR)),
            ),
            Pair(
                QueryForm("3", "5", "100", "Fizz", "Fizz"),
                QueryFormValidation(false, QueryFormValidationError(firstWordError = QueryFormValidationError.SAME_WORD_ERROR, secondWordError = QueryFormValidationError.SAME_WORD_ERROR)),
            ),
            Pair(
                QueryForm("3", "5", "100", "Fizz", "Buzz"),
                QueryFormValidation(true),
            ),
            Pair(
                QueryForm("-3", "-5", "100", "Fizz", "Buzz"),
                QueryFormValidation(
                    false,
                    QueryFormValidationError(
                        firstNumberError = QueryFormValidationError.NEGATIVE_NUMBER_INPUT_ERROR,
                        secondNumberError = QueryFormValidationError.NEGATIVE_NUMBER_INPUT_ERROR,
                    ),
                ),
            ),
            Pair(
                QueryForm("-3", "-5", "-100", "Fizz", "Buzz4"),
                QueryFormValidation(
                    false,
                    QueryFormValidationError(
                        firstNumberError = QueryFormValidationError.NEGATIVE_NUMBER_INPUT_ERROR,
                        secondNumberError = QueryFormValidationError.NEGATIVE_NUMBER_INPUT_ERROR,
                        limitError = QueryFormValidationError.NEGATIVE_LIMIT_INPUT_ERROR,
                        secondWordError = QueryFormValidationError.WORD_INPUT_ERROR,
                    ),
                ),
            ),
            Pair(
                QueryForm("-3", "-3", "-1100", "Fizz", "Buzz4"),
                QueryFormValidation(
                    false,
                    QueryFormValidationError(
                        firstNumberError = QueryFormValidationError.NEGATIVE_NUMBER_INPUT_ERROR + "\n" + QueryFormValidationError.SAME_NUMBER_ERROR,
                        secondNumberError = QueryFormValidationError.NEGATIVE_NUMBER_INPUT_ERROR + "\n" + QueryFormValidationError.SAME_NUMBER_ERROR,
                        limitError = QueryFormValidationError.NEGATIVE_LIMIT_INPUT_ERROR,
                        secondWordError = QueryFormValidationError.WORD_INPUT_ERROR,
                    ),
                ),
            ),
            Pair(
                QueryForm("0", "0", "-100", "", "   "),
                QueryFormValidation(
                    false,
                    QueryFormValidationError(
                        firstNumberError = QueryFormValidationError.NEGATIVE_NUMBER_INPUT_ERROR + "\n" + QueryFormValidationError.SAME_NUMBER_ERROR,
                        secondNumberError = QueryFormValidationError.NEGATIVE_NUMBER_INPUT_ERROR + "\n" + QueryFormValidationError.SAME_NUMBER_ERROR,
                        limitError = QueryFormValidationError.NEGATIVE_LIMIT_INPUT_ERROR,
                        firstWordError = QueryFormValidationError.EMPTY_WORD_ERROR,
                        secondWordError = QueryFormValidationError.EMPTY_WORD_ERROR + "\n" + QueryFormValidationError.WORD_INPUT_ERROR,
                    ),
                ),
            ),
            Pair(
                QueryForm("0", "0", "-100", "", ""),
                QueryFormValidation(
                    false,
                    QueryFormValidationError(
                        firstNumberError = QueryFormValidationError.NEGATIVE_NUMBER_INPUT_ERROR + "\n" + QueryFormValidationError.SAME_NUMBER_ERROR,

                        secondNumberError = QueryFormValidationError.NEGATIVE_NUMBER_INPUT_ERROR + "\n" + QueryFormValidationError.SAME_NUMBER_ERROR,
                        limitError = QueryFormValidationError.NEGATIVE_LIMIT_INPUT_ERROR,
                        firstWordError = QueryFormValidationError.EMPTY_WORD_ERROR,
                        secondWordError = QueryFormValidationError.EMPTY_WORD_ERROR,
                    ),
                ),
            ),
        )
    }
}


