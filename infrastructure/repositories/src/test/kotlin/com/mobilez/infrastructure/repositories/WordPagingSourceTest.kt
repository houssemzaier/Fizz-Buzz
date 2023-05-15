package com.mobilez.infrastructure.repositories

import androidx.paging.PagingSource
import com.google.common.truth.Truth.assertThat
import com.mobilez.fizzbuzz.domain.models.Query
import com.mobilez.fizzbuzz.domain.models.Word
import com.mobilez.fizzbuzz.domain.services.NumberTransformer
import com.mobilez.infrastructure.data_source.WordPagingSource
import junit.framework.TestCase.fail
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class WordPagingSourceTest {

    private lateinit var numberTransformer: NumberTransformer
    private lateinit var query: Query
    private lateinit var wordPagingSource: WordPagingSource

    @Before
    fun setUp() {
        numberTransformer = mock()
        query = Query(2, 3, 20, "Fizz", "Buzz")
        wordPagingSource = WordPagingSource(numberTransformer, query)
    }

    @Test
    fun `given start key, when load is called, then return correct LoadResult`() = runTest {
        // Arrange
        val params = PagingSource.LoadParams.Refresh(1, 10, false)
        val expectedWords = listOf(
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
        `when`(numberTransformer.getWordListByRange(query, 1 until 11)).thenReturn(expectedWords)

        // Act
        val result = wordPagingSource.load(params)

        // Assert
        if (result is PagingSource.LoadResult.Page) {
            assertThat(result.data).isEqualTo(expectedWords)
            assertThat(result.prevKey).isNull()
            assertThat(result.nextKey).isEqualTo(11)
        } else {
            fail("Result should be an instance of LoadResult.Page")
        }
    }
}
