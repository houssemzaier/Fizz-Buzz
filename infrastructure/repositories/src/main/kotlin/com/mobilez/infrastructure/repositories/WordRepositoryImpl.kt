package com.mobilez.infrastructure.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mobilez.fizzbuzz.domain.models.Query
import com.mobilez.fizzbuzz.domain.models.Word
import com.mobilez.fizzbuzz.domain.repositories.WordRepository
import com.mobilez.fizzbuzz.domain.services.NumberTransformer
import com.mobilez.infrastructure.data_source.WordPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlin.math.min

class WordRepositoryImpl @Inject constructor(
    private val numberTransformer: NumberTransformer,
) : WordRepository {

    private companion object {
        private const val PAGE_SIZE = 10
    }

    override fun loadWordList(query: Query): Flow<PagingData<Word>> = Pager(
        config = PagingConfig(
            pageSize = PAGE_SIZE,
            enablePlaceholders = false,
            initialLoadSize = min(query.limit, PAGE_SIZE),
        ),
        pagingSourceFactory = {
            WordPagingSource(
                numberTransformer = numberTransformer,
                query = query,
            )
        },
    ).flow
}
