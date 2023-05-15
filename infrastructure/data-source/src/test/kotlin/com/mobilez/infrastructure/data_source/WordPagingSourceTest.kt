package com.mobilez.infrastructure.data_source

import androidx.paging.PagingSource
import com.google.common.truth.Truth.assertThat
import com.mobilez.fizzbuzz.domain.models.Query
import com.mobilez.fizzbuzz.domain.models.Word
import com.mobilez.fizzbuzz.domain.services.NumberTransformer
import com.mobilez.infrastructure.data_source.WordPagingSource.Companion.LOAD_SIZE
import kotlinx.coroutines.test.runTest
import org.junit.Test

class WordPagingSourceTest {
    private lateinit var sut: WordPagingSource

    private companion object {
        val DEFAULT_QUERY = Query(
            firstNumber = 3,
            secondNumber = 5,
            limit = 1_000,
            firstWord = "fizz",
            secondWord = "buzz",
        )
    }

    @Test
    fun `check initial load result`() = runTest {
        // Arrange
        sut = WordPagingSource(NumberTransformer(), DEFAULT_QUERY)

        // Act
        val loadResult = sut.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = LOAD_SIZE,
                placeholdersEnabled = false,
            ),
        )

        // Assert
        val loadResultExpected: PagingSource.LoadResult.Page<Int, Word> =
            PagingSource.LoadResult.Page(
                data = NumberTransformer().getWordListByRange(DEFAULT_QUERY, 1..10),
                prevKey = null,
                nextKey = 11,
            )

        assertThat(loadResult).isEqualTo(loadResultExpected)
    }

    @Test
    fun `check last load result`() = runTest {
        // Arrange
        sut = WordPagingSource(NumberTransformer(), DEFAULT_QUERY)

        // Act
        val loadResult = sut.load(
            PagingSource.LoadParams.Refresh(
                key = DEFAULT_QUERY.limit,
                loadSize = LOAD_SIZE,
                placeholdersEnabled = false,
            ),
        )

        // Assert
        val loadResultExpected: PagingSource.LoadResult.Page<Int, Word> =
            PagingSource.LoadResult.Page(
                data = NumberTransformer().getWordListByRange(
                    DEFAULT_QUERY,
                    DEFAULT_QUERY.limit..DEFAULT_QUERY.limit,
                ),
                prevKey = DEFAULT_QUERY.limit - LOAD_SIZE,
                nextKey = null,
            )

        assertThat(loadResult).isEqualTo(loadResultExpected)
    }


    @Test
    fun `check in the middle load result`() = runTest {
        // Arrange
        sut = WordPagingSource(NumberTransformer(), DEFAULT_QUERY)

        // Act
        val loadResult = sut.load(
            PagingSource.LoadParams.Refresh(
                key = 55,
                loadSize = LOAD_SIZE,
                placeholdersEnabled = false,
            ),
        )

        // Assert
        val loadResultExpected: PagingSource.LoadResult.Page<Int, Word> =
            PagingSource.LoadResult.Page(
                data = NumberTransformer().getWordListByRange(
                    DEFAULT_QUERY,
                    idsRange = 55 until 55 + LOAD_SIZE,
                ),
                prevKey = 55 - LOAD_SIZE,
                nextKey = 55 + LOAD_SIZE,
            )

        assertThat(loadResult).isEqualTo(loadResultExpected)
    }
}
