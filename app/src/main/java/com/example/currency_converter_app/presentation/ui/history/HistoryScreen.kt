package com.example.currency_converter_app.presentation.ui.history

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HistoryScreen(historyViewModel: HistoryViewModel) {
    BackHandler(onBack = { historyViewModel.setEvent(event = HistoryEvent.NavigateUp) })

    historyViewModel.setEvent(event = HistoryEvent.GetHistory)

    val historyState = historyViewModel.viewState.collectAsState().value

    if (historyState.historyList.isEmpty()) {
        ShowBackText(historyViewModel = historyViewModel)

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ShowEmptyText()
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            item {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    ShowBackText(historyViewModel = historyViewModel)
                }
            }
            item { ShowHeader() }

            items(
                items = historyState.historyList,
                key = { history -> history.id }
            ) { history ->
                HistoryRow(historyModel = history)
            }
        }
    }
}

@Composable
private fun ShowHeader() {
    Text(
        text = "History",
        fontSize = 22.sp,
        color = Color.Black
    )
}

@Composable
private fun ShowEmptyText() {
    Text(
        text = "There are no saved data",
        fontSize = 22.sp,
        color = Color.Black
    )
}

@Composable
private fun ShowBackText(historyViewModel: HistoryViewModel) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .padding(start = 24.dp, top = 24.dp)
            .clickable(onClick = { historyViewModel.setEvent(HistoryEvent.NavigateUp) })
    ) {
        Text(
            text = "Back",
            fontSize = 24.sp,
            color = Color.Black
        )
    }
}