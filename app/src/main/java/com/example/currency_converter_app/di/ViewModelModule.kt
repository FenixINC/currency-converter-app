package com.example.currency_converter_app.di

import com.example.currency_converter_app.presentation.main.MainViewModel
import com.example.currency_converter_app.presentation.ui.history.HistoryViewModel
import com.example.currency_converter_app.presentation.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun viewModelModule() = module {
    viewModel { MainViewModel(navigator = get()) }
    viewModel {
        HomeViewModel(
            navigator = get(),
            getCurrencyUseCase = get(),
            getCurrencyConvertResultUseCase = get(),
            loadCurrencyUseCase = get(),
            convertCurrencyUseCase = get()
        )
    }
    viewModel {
        HistoryViewModel(
            navigator = get(),
            historyUseCase = get()
        )
    }
}