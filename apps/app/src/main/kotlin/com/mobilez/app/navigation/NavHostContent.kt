package com.mobilez.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.mobilez.feature.form.FormUiModel
import com.mobilez.feature.form.FormScreen
import com.mobilez.feature.form.FormScreenViewModel
import com.mobilez.feature.result.ResultScreen
import com.mobilez.feature.result.ResultScreenViewModel
import com.mobilez.fizzbuzz.domain.models.Query

@Composable
fun NavHostContent() {
    val navController = rememberNavController()

    val formScreenViewModel = hiltViewModel<FormScreenViewModel>()
    val formData by formScreenViewModel.formUiModel.collectAsState()
    val resultScreenViewModel = hiltViewModel<ResultScreenViewModel>()
    val words = resultScreenViewModel.words.collectAsLazyPagingItems()

    val onValueChange: (FormUiModel) -> Unit = { state -> formScreenViewModel.updateFormData(state) }
    val onSubmitClick: (Query) -> Unit = {
        navController.navigate(Destination.RESULT_SCREEN.route)
        resultScreenViewModel.sendQuery(it)
    }
    NavHost(
        navController = navController,
        startDestination = Destination.FORM_SCREEN.route,
    ) {
        composable(Destination.FORM_SCREEN.route) {
            FormScreen(
                formData,
                onValueChange,
                onSubmitClick,
            )
        }
        composable(Destination.RESULT_SCREEN.route) {
            ResultScreen(words)
        }
    }
}
