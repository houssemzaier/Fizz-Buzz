package com.mobilez.feature.result

import androidx.compose.runtime.Composable
import androidx.paging.compose.LazyPagingItems
import com.mobilez.design.components.WordUiModel

@Composable
fun ResultScreen(words: LazyPagingItems<WordUiModel>) {
    WordLazyPagingItemsContent(words)
}
