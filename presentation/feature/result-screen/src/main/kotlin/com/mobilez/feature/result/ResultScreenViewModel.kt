package com.mobilez.feature.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.mobilez.design.components.WordUiModel
import com.mobilez.domain.usecases.result.GetWordsUseCase
import com.mobilez.fizzbuzz.domain.models.Query
import com.mobilez.fizzbuzz.domain.models.Word
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultScreenViewModel @Inject constructor(
    private val getWordsUseCase: GetWordsUseCase,
) : ViewModel() {
    private companion object {
        fun Word.toUiModel() = WordUiModel(text = this.text)
    }

    private val _querySharedFlow = MutableSharedFlow<Query>()

    val words: Flow<PagingData<WordUiModel>> = _querySharedFlow.flatMapLatest {
        getWordsUseCase(it)
    }
        .map(::mapToUiPagingData)
        .cachedIn(viewModelScope)
        .flowOn(Default)

    fun sendQuery(query: Query) {
        viewModelScope.launch {
            _querySharedFlow.emit(query)
        }
    }

    private fun mapToUiPagingData(pagingData: PagingData<Word>): PagingData<WordUiModel> =
        pagingData.map { word ->
            word.toUiModel()
        }
}
