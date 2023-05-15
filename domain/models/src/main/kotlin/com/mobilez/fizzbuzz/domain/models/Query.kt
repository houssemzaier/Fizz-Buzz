package com.mobilez.fizzbuzz.domain.models

data class Query(
    val firstNumber: Int,
    val secondNumber: Int,
    val limit: Int,
    val firstWord: String,
    val secondWord: String,
)
