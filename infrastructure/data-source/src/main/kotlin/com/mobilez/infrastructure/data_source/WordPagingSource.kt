package com.mobilez.infrastructure.data_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mobilez.fizzbuzz.domain.models.Query
import com.mobilez.fizzbuzz.domain.models.Word
import com.mobilez.fizzbuzz.domain.services.NumberTransformer
import javax.inject.Inject
import kotlin.math.max
import kotlin.math.min

class WordPagingSource @Inject constructor(
    private val numberTransformer: NumberTransformer,
    private val query: Query,
) : PagingSource<Int, Word>() {

    internal companion object {
        private const val STARTING_KEY = 1
        private val TAG = WordPagingSource::class.java.simpleName
        internal const val LOAD_SIZE = 10
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Word> {
        val startKey = params.key ?: STARTING_KEY
        val nextKey = getNextKeyOrNull(startKey + LOAD_SIZE, query.limit)

        //data range
        val idsRange = getIdsRange(startKey)

        val wordList = numberTransformer.getWordListByRange(query, idsRange)

        return LoadResult.Page(
            data = wordList,
            prevKey = getPreviousKeyOrNull(startKey),
            nextKey = nextKey,
        )
    }

    /**
     * Makes sure that the range doesn't exceed the query's limit
     */
    private fun getIdsRange(startKey: Int) =
        startKey.until(min(startKey + LOAD_SIZE, query.limit.plus(1)))

    /**
     * Makes sure that NextKey doesn't exceed the query's limit
     */
    private fun getNextKeyOrNull(nextKey: Int, limit: Int): Int? =
        if (nextKey > limit) null
        else nextKey

    /**
     * Makes sure that previousKey is never less than [STARTING_KEY]
     */
    private fun getPreviousKeyOrNull(startKey: Int): Int? {
        if (startKey == STARTING_KEY) {
            return null
        }

        val validPrevKey = max(STARTING_KEY, startKey - LOAD_SIZE)
        return if (validPrevKey == STARTING_KEY) {
            null
        } else {
            validPrevKey
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Word>): Int? = state.anchorPosition
}
