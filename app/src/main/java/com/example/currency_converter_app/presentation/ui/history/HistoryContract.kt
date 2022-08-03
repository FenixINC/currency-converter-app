package com.example.currency_converter_app.presentation.ui.history

import com.example.currency_converter_app.data.database.HistoryModel
import com.example.currency_converter_app.presentation.base.ViewEffect
import com.example.currency_converter_app.presentation.base.ViewEvent
import com.example.currency_converter_app.presentation.base.ViewState

sealed class HistoryEvent : ViewEvent {
    object GetHistory : HistoryEvent()
    object NavigateUp : HistoryEvent()
}

data class HistoryState(val historyList: List<HistoryModel> = emptyList()) : ViewState

sealed class HistoryEffect : ViewEffect