import androidx.paging.PagingData
import app.cash.turbine.test
import com.mobilez.domain.usecases.result.GetWordsUseCase
import com.mobilez.feature.result.MainDispatcherRule
import com.mobilez.feature.result.ResultScreenViewModel
import com.mobilez.fizzbuzz.domain.models.Query
import com.mobilez.fizzbuzz.domain.models.Word
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class ResultScreenViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var getWordsUseCase: GetWordsUseCase
    private lateinit var viewModel: ResultScreenViewModel

    @Before
    fun setup() {
        getWordsUseCase = mock()
        viewModel = ResultScreenViewModel(getWordsUseCase)
    }

    @Test
    fun `When sendQuery is called, words flow should emit correct data`() = runTest {

        val query = Query(1, 2, 10, "firstWord", "secondWord")
        val words = listOf(Word(1, "word1"), Word(2, "word2"))
        val pagingData = PagingData.from(words)

        whenever(getWordsUseCase(query)).thenReturn(flowOf(pagingData))

        launch { viewModel.sendQuery(query) }

        viewModel.words.test {
            ensureAllEventsConsumed()
        }
    }
}
