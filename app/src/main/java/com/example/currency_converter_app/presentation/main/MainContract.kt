package com.example.currency_converter_app.presentation.main

import androidx.navigation.NavOptionsBuilder
import com.example.currency_converter_app.presentation.base.ViewEffect
import com.example.currency_converter_app.presentation.base.ViewEvent
import com.example.currency_converter_app.presentation.base.ViewState

data class MainState(
    val isLoading: Boolean = false,
) : ViewState

sealed class MainEvent : ViewEvent {
    object Idle : MainEvent()
}

sealed class MainEffect : ViewEffect {
    data class Navigate(
        val destination: String,
        val builder: NavOptionsBuilder.() -> Unit
    ) : MainEffect()

    object NavigateUp : MainEffect()
    object CloseApp : MainEffect()

    data class InternetConnection(val isSuccess: Boolean) : MainEffect()
}