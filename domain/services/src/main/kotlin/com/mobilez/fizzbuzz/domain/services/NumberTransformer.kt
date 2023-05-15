package com.mobilez.fizzbuzz.domain.services

import com.mobilez.fizzbuzz.domain.models.Query
import com.mobilez.fizzbuzz.domain.models.Word
import javax.inject.Inject

class NumberTransformer @Inject constructor() {

    fun getWordListByRange(query: Query, idsRange: IntRange): List<Word> = idsRange.map { id ->
        Word(
            id = id,
            text = id.transform(
                query.firstNumber,
                query.secondNumber,
                query.firstWord,
                query.secondWord,
            ),
        )
    }

    private fun Int.transform(int1: Int, int2: Int, str1: String, str2: String): String = when {
        isMultipleOf(int1 * int2) -> str1 + str2
        isMultipleOf(int1) -> str1
        isMultipleOf(int2) -> str2
        else -> this.toString()
    }

    private fun Int.isMultipleOf(int: Int) = this.rem(int) == 0
}
