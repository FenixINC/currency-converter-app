package com.example.currency_converter_app.presentation.ui.history

import androidx.lifecycle.viewModelScope
import com.example.currency_converter_app.domain.usecase.HistoryUseCase
import com.example.currency_converter_app.presentation.base.BaseViewModel
import com.example.currency_converter_app.presentation.navigation.Navigator
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private const val DEFAULT_HISTORY_COUNT = 10

class HistoryViewModel(
    private val navigator: Navigator,
    private val historyUseCase: HistoryUseCase
) : BaseViewModel<HistoryEvent, HistoryState, HistoryEffect>(), Navigator by navigator {

    override fun setInitialState(): HistoryState = HistoryState()

    override fun handleEvents(event: HistoryEvent) {
        when (event) {
            is HistoryEvent.NavigateUp -> {
                navigator.navigateUp()
            }
            is HistoryEvent.GetHistory -> {
                loadHistory()
            }
        }
    }

    private fun loadHistory() {
        viewModelScope.launch {
            kotlin.runCatching {
                historyUseCase()
            }.onSuccess { historyFlow ->
                historyFlow.collectLatest { history ->
                    val list = history
                        .takeLast(n = DEFAULT_HISTORY_COUNT)
                        .sortedByDescending { it.date }
                    setState {
                        copy(historyList = list)
                    }
                }
            }.onFailure { throwable ->
                navigator.onError(throwable = throwable)
            }
        }
    }
}