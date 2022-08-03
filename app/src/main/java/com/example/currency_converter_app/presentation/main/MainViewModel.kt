package com.example.currency_converter_app.presentation.main

import androidx.lifecycle.viewModelScope
import com.example.currency_converter_app.presentation.base.BaseViewModel
import com.example.currency_converter_app.presentation.navigation.Navigator
import com.example.currency_converter_app.presentation.navigation.NavigatorEvent
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainViewModel(
    private val navigator: Navigator
) : BaseViewModel<MainEvent, MainState, MainEffect>(), Navigator by navigator {

    init {
        handleNavigation()
    }

    override fun setInitialState() = MainState()

    override fun handleEvents(event: MainEvent) {
        when (event) {
            is MainEvent.Idle -> {}
        }
    }

    private fun handleNavigation() {
        viewModelScope.launch {
            navigator.destinations.collectLatest { navigationEvent ->
                when (navigationEvent) {
                    is NavigatorEvent.CloseApp -> setEffect { MainEffect.CloseApp }
                    is NavigatorEvent.NavigateUp -> setEffect { MainEffect.NavigateUp }
                    is NavigatorEvent.Error -> {
                        // TODO: show error
                    }
                    // TODO: Implement internet connection
//                    is NavigatorEvent.InternetConnectionState -> {
//                        when (navigationEvent.networkStatusResultState) {
//                            is NetworkStatusResultState.Success -> {
//                                setEffect { MainEffect.InternetConnection(isSuccess = true) }
//                            }
//                            is NetworkStatusResultState.Error -> {
//                                setEffect { MainEffect.InternetConnection(isSuccess = false) }
//                            }
//                        }
//                    }
                    is NavigatorEvent.Directions -> {
                        setEffect {
                            MainEffect.Navigate(
                                destination = navigationEvent.destination,
                                builder = navigationEvent.builder
                            )
                        }
                    }
                }
            }
        }
    }
}