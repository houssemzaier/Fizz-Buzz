package com.mobilez.fizzbuzz.domain.repositories

import androidx.paging.PagingData
import com.mobilez.fizzbuzz.domain.models.Word
import com.mobilez.fizzbuzz.domain.models.Query
import kotlinx.coroutines.flow.Flow

interface WordRepository {
    fun loadWordList(query: Query): Flow<PagingData<Word>>
}
