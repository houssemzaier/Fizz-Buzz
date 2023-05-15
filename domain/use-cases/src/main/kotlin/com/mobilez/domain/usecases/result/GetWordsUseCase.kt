package com.mobilez.domain.usecases.result

import androidx.paging.PagingData
import com.mobilez.fizzbuzz.domain.models.Query
import com.mobilez.fizzbuzz.domain.models.Word
import com.mobilez.fizzbuzz.domain.repositories.WordRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWordsUseCase @Inject constructor(
    private val repository: WordRepository,
) {
    operator fun invoke(query: Query): Flow<PagingData<Word>> =
        repository.loadWordList(query)
}
