package com.mobilez.feature.result

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.mobilez.design.components.WordItemContent
import com.mobilez.design.components.WordUiModel

@Composable
fun WordLazyPagingItemsContent(
    words: LazyPagingItems<WordUiModel>,
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = words.loadState) {
        if (words.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (words.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG,
            ).show()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (words.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                items(
                    count = words.itemCount,
                    key = words.itemKey(),
                    contentType = words.itemContentType(
                    ),
                ) { index ->
                    val item = words[index]
                    if (item != null) {
                        WordItemContent(
                            word = item,
                            modifier = Modifier.fillMaxWidth(),
                        )
                    }
                }
                item {
                    if (words.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}


