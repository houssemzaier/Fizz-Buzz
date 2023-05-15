package com.mobilez.catalog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mobilez.design.components.FizzBuzzFormTextField
import com.mobilez.design.components.WordItemContent
import com.mobilez.design.components.WordUiModel
import com.mobilez.design.theme.FizzBuzzTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FizzBuzzTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .verticalScroll(rememberScrollState())
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            modifier = Modifier.padding(vertical = 10.dp),
                            text = "These are our catalog components",
                            style = MaterialTheme.typography.titleLarge,
                        )
                        Divider()
                        FizzBuzzFormTextField(
                            value = "test",
                            onValueChange = {},
                            label = "test",
                            isError = false,
                            errorText = "test",
                        )
                        Divider()
                        WordItemContent(
                            Modifier,
                            WordUiModel("FIZZBUZZ"),
                        )
                    }
                }
            }
        }
    }
}
