package com.mobilez.fizzbuzz.domain.services

import com.google.common.truth.Truth.assertThat
import com.mobilez.fizzbuzz.domain.models.Query
import com.mobilez.fizzbuzz.domain.models.Word
import org.junit.Test

class NumberTransformerTest {
    private val numberTransformer = NumberTransformer()

    @Test
    fun getWordListByRange_correctOutput() {
        val range = 1..10
        val query = Query(2, 3, 10, "Fizz", "Buzz")
        val expected = listOf(
            Word(1, "1"),
            Word(2, "Fizz"),
            Word(3, "Buzz"),
            Word(4, "Fizz"),
            Word(5, "5"),
            Word(6, "FizzBuzz"),
            Word(7, "7"),
            Word(8, "Fizz"),
            Word(9, "Buzz"),
            Word(10, "Fizz"),
        )

        val actual = numberTransformer.getWordListByRange(query, range)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun transform_firstAndSecondNumber_multiple() {
        val input = 6
        val expected = "FizzBuzz"

        val actual = numberTransformer.getWordListByRange(
            Query(2, 3, 10, "Fizz", "Buzz"),
            input..input,
        ).first().text

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun transform_onlyFirstNumber_multiple() {
        val input = 4
        val expected = "Fizz"

        val actual = numberTransformer.getWordListByRange(
            Query(2, 3, 10, "Fizz", "Buzz"),
            input..input,
        ).first().text

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun transform_onlySecondNumber_multiple() {
        val input = 9
        val expected = "Buzz"

        val actual = numberTransformer.getWordListByRange(
            Query(2, 3, 10, "Fizz", "Buzz"),
            input..input,
        ).first().text

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun transform_noMultiples() {
        val input = 7
        val expected = "7"

        val actual = numberTransformer.getWordListByRange(
            Query(2, 3, 10, "Fizz", "Buzz"),
            input..input,
        ).first().text

        assertThat(actual).isEqualTo(expected)
    }
}
