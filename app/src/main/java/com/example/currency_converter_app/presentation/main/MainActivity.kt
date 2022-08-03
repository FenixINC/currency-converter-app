package com.example.currency_converter_app.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.currency_converter_app.presentation.navigation.destination.HistoryDestination
import com.example.currency_converter_app.presentation.navigation.destination.HomeDestination
import com.example.currency_converter_app.presentation.ui.history.HistoryScreen
import com.example.currency_converter_app.presentation.ui.history.HistoryViewModel
import com.example.currency_converter_app.presentation.ui.home.HomeScreen
import com.example.currency_converter_app.presentation.ui.home.HomeViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainActivity : ComponentActivity(), KoinComponent {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val mainViewModel by inject<MainViewModel>()
            val homeViewModel by inject<HomeViewModel>()
            val historyViewModel by inject<HistoryViewModel>()

            val navControllerState = rememberNavController()

            LaunchedEffect(key1 = Unit) {
                lifecycleScope.launch {
                    repeatOnLifecycle(state = Lifecycle.State.CREATED) {
                        mainViewModel.viewState.collectLatest { mainState ->
                            setContent {
                                NavHost(
                                    navController = navControllerState,
                                    startDestination = HomeDestination.route()
                                ) {
                                    composable(route = HomeDestination.route()) {
                                        HomeScreen(homeViewModel = homeViewModel)
                                    }
                                    composable(route = HistoryDestination.route()) {
                                        HistoryScreen(historyViewModel = historyViewModel)
                                    }
                                }
                            }
                        }
                    }
                }
            }

            LaunchedEffect(navControllerState) {
                lifecycleScope.launch {
                    repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                        mainViewModel.effect.collectLatest { effect ->
                            when (effect) {
                                is MainEffect.CloseApp -> {
                                    this@MainActivity.finish()
                                }
                                is MainEffect.NavigateUp -> {
                                    navControllerState.navigateUp()
                                }
                                is MainEffect.Navigate -> {
                                    navControllerState.navigate(
                                        route = effect.destination,
                                        builder = effect.builder
                                    )
                                }
                                is MainEffect.InternetConnection -> {
                                    when (effect.isSuccess) {
                                        true -> {
                                            // TODO: success connect
                                        }
                                        false -> {
                                            // TODO: show internet connection error
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
