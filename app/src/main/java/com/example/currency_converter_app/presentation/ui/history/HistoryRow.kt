package com.example.currency_converter_app.presentation.ui.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.currency_converter_app.R
import com.example.currency_converter_app.data.database.HistoryModel

@Composable
fun HistoryRow(historyModel: HistoryModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp),
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(all = 8.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.wrapContentSize()) {
                    Text(
                        text = "Original currency",
                        fontSize = 18.sp,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(height = 6.dp))

                    Text(
                        text = historyModel.originalCurrency,
                        fontSize = 20.sp,
                        color = colorResource(id = R.color.green)
                    )
                }

                Column(modifier = Modifier.wrapContentSize()) {
                    Text(
                        text = "Target currency",
                        fontSize = 18.sp,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(height = 6.dp))

                    Text(
                        text = historyModel.targetCurrency,
                        fontSize = 20.sp,
                        color = colorResource(id = R.color.green)
                    )
                }
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height = 4.dp)
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height = 1.dp)
                    .background(color = Color.Black)
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height = 4.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.wrapContentSize()) {
                    Text(
                        text = "Amount",
                        fontSize = 18.sp,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(height = 6.dp))

                    Text(
                        text = historyModel.amount,
                        fontSize = 20.sp,
                        color = colorResource(id = R.color.green)
                    )
                }

                Column(modifier = Modifier.wrapContentSize()) {
                    Text(
                        text = "Convert result",
                        fontSize = 18.sp,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(height = 6.dp))

                    Text(
                        text = historyModel.result,
                        fontSize = 20.sp,
                        color = colorResource(id = R.color.green)
                    )
                }
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height = 12.dp)
            )

            Text(
                text = historyModel.date,
                fontSize = 18.sp,
                color = colorResource(id = R.color.greyDark)
            )
        }
    }
}