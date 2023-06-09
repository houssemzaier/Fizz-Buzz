package com.mobilez.design.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun WordItemContent(modifier: Modifier = Modifier, word: WordUiModel) {
    Card(
        modifier = modifier
            .wrapContentHeight()
            .wrapContentWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = word.text,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}


@Preview(name = "Phone Preview", widthDp = 360, heightDp = 640)
@Preview(name = "Small Tablet Preview", widthDp = 600, heightDp = 1024)
@Preview(name = "Large Tablet Preview", widthDp = 800, heightDp = 1280)
@Composable
private fun StationContentCard_Prev() {
    WordItemContent(
        Modifier,
        WordUiModel("FIZZBUZZ"),
    )
}
