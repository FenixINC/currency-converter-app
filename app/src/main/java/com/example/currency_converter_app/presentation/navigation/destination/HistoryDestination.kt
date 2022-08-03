package com.example.currency_converter_app.presentation.navigation.destination

import com.example.navigation.NavigationDestination

private const val ROUTE_HISTORY = "route_history"

object HistoryDestination : NavigationDestination {
    override fun route(): String = ROUTE_HISTORY
}