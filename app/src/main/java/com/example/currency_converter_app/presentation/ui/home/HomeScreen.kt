package com.example.currency_converter_app.presentation.ui.home

import android.app.DatePickerDialog
import android.widget.DatePicker
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.currency_converter_app.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import java.util.Calendar
import java.util.Date

private const val ONE_MONTH = 1

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {
    BackHandler(onBack = { homeViewModel.setEvent(event = HomeEvent.CloseApp) })

    homeViewModel.setEvent(event = HomeEvent.LoadAllCurrencies)

    val homeState = homeViewModel.viewState.collectAsState().value
    val homeEffect = homeViewModel.effect

    val fromCurrencyState = remember { mutableStateOf(value = "") }
    val toCurrencyState = remember { mutableStateOf(value = "") }
    val currencies = homeState.currencies

    HandleEffect(homeEffect = homeEffect)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        item {
            ShowHeader()
        }

        item {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height = 20.dp)
            )
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Choose date",
                    fontSize = 18.sp,
                    color = Color.Black
                )

                ShowHistory(homeViewModel = homeViewModel)
            }
        }

        item {
            ShowDate(homeViewModel = homeViewModel)
        }

        item {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height = 30.dp)
            )
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .width(width = 150.dp)
                        .wrapContentHeight()
                ) {
                    Text(
                        text = "From",
                        fontSize = 18.sp,
                        color = Color.Black
                    )

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(height = 10.dp)
                    )

                    if (!currencies.isNullOrEmpty() && currencies.isNotEmpty()) {
                        val currencyState =
                            remember { mutableStateOf(value = currencies.get(index = 0)) }
                        var expanded by remember { mutableStateOf(value = false) }

                        ExposedDropdownMenuBox(
                            expanded = expanded,
                            onExpandedChange = {
                                expanded = !expanded
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                        ) {
                            TextField(
                                readOnly = true,
                                value = currencyState.value.name,
                                onValueChange = {},
                                label = { Text("Currency") },
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                                },
                                colors = ExposedDropdownMenuDefaults.textFieldColors()
                            )
                            ExposedDropdownMenu(
                                expanded = expanded,
                                onDismissRequest = {
                                    expanded = false
                                }
                            ) {
                                currencies.forEach { selectionOption ->
                                    DropdownMenuItem(
                                        onClick = {
                                            currencyState.value = selectionOption
                                            fromCurrencyState.value = selectionOption.name
                                            expanded = false
                                            homeViewModel.setEvent(
                                                event = HomeEvent.SaveFromCurrency(currency = selectionOption.name)
                                            )
                                        }
                                    ) {
                                        Text(text = selectionOption.name)
                                    }
                                }
                            }
                        }
                    }
                }

                Column(
                    modifier = Modifier
                        .width(width = 150.dp)
                        .wrapContentHeight()
                ) {
                    Text(
                        text = "To",
                        fontSize = 18.sp,
                        color = Color.Black
                    )

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(height = 10.dp)
                    )

                    if (!currencies.isNullOrEmpty() && currencies.isNotEmpty()) {
                        val currencyState =
                            remember { mutableStateOf(value = currencies.get(index = 0)) }
                        var expanded by remember { mutableStateOf(value = false) }

                        ExposedDropdownMenuBox(
                            expanded = expanded,
                            onExpandedChange = {
                                expanded = !expanded
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                        ) {
                            TextField(
                                readOnly = true,
                                value = currencyState.value.name,
                                onValueChange = {},
                                label = { Text("Currency") },
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(
                                        expanded = expanded
                                    )
                                },
                                colors = ExposedDropdownMenuDefaults.textFieldColors()
                            )
                            ExposedDropdownMenu(
                                expanded = expanded,
                                onDismissRequest = {
                                    expanded = false
                                }
                            ) {
                                currencies.forEach { selectionOption ->
                                    DropdownMenuItem(
                                        onClick = {
                                            currencyState.value = selectionOption
                                            toCurrencyState.value = selectionOption.name
                                            expanded = false
                                            homeViewModel.setEvent(
                                                event = HomeEvent.SaveToCurrency(currency = selectionOption.name)
                                            )
                                        }
                                    ) {
                                        Text(text = selectionOption.name)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        item {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height = 30.dp)
            )
        }

        item {
            ShowAmount(homeViewModel = homeViewModel)
        }

        item {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height = 40.dp)
            )
        }

        item {
            Button(
                onClick = {
                    homeViewModel.setEvent(
                        event = HomeEvent.ConvertCurrencies(
                            fromCurrency = fromCurrencyState.value,
                            toCurrency = toCurrencyState.value
                        )
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height = 56.dp),
                shape = RoundedCornerShape(size = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.green)
                )
            ) {
                Text(
                    text = "Convert",
                    fontSize = 20.sp,
                    color = colorResource(id = R.color.white)
                )
            }
        }

        item {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height = 40.dp)
            )
        }

        item {
            ShowResult(homeState = homeState)
        }
    }
}

@Composable
private fun HandleEffect(homeEffect: Flow<HomeEffect>) {
    val context = LocalContext.current

    LaunchedEffect(key1 = Unit) {
        homeEffect.collectLatest { homeEffect ->
            when (homeEffect) {
                is HomeEffect.ShowError -> {
                    Toast.makeText(
                        context,
                        homeEffect.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}

@Composable
private fun ShowHeader() {
    Text(
        text = stringResource(id = R.string.header_currency_converter),
        fontSize = 22.sp,
        color = Color.Black
    )
}

@Composable
private fun ShowDate(homeViewModel: HomeViewModel) {
    val context = LocalContext.current

    val year: Int
    val month: Int
    val day: Int

    val calendar = Calendar.getInstance()

    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)

    calendar.time = Date()

    val date = remember { mutableStateOf(value = "Enter date") }

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            val correctMonth = mMonth + ONE_MONTH
            val finalYear = if (mYear < 10) "0$mYear" else mYear
            val finalMonth = if (correctMonth < 10) "0$correctMonth" else correctMonth
            val finalDay = if (mDayOfMonth < 10) "0$mDayOfMonth" else mDayOfMonth

            date.value = "$finalDay/$finalMonth/$finalYear"

            homeViewModel.setEvent(event = HomeEvent.SaveDate(date = "$finalYear/$finalMonth/$finalDay"))
        }, year, month, day
    ).apply { datePicker.maxDate = System.currentTimeMillis() }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 10.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 50.dp)
                .background(color = colorResource(id = R.color.grey))
                .clickable(onClick = { datePickerDialog.show() })
                .padding(start = 8.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = date.value,
                fontSize = 18.sp,
                color = colorResource(id = R.color.greyDark)
            )
        }
    }
}

@Composable
private fun ShowHistory(homeViewModel: HomeViewModel) {
    Box(
        modifier = Modifier
            .width(width = 150.dp)
            .wrapContentHeight()
            .clickable(
                onClick = { homeViewModel.setEvent(event = HomeEvent.OpenHistoryScreen) }
            )
    ) {
        Text(
            text = "Show History",
            color = Color.Blue,
            fontSize = 20.sp,
            modifier = Modifier.fillMaxWidth()
        )
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun ShowAmount(homeViewModel: HomeViewModel) {
    var text by remember { mutableStateOf(TextFieldValue(text = "")) }
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.None,
            autoCorrect = true,
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { keyboardController?.hide() }
        ),
        value = text,
        label = { Text(text = "Amount") },
        onValueChange = {
            text = it
            if (it.text.isNotEmpty()) {
                runCatching {
                    homeViewModel.setEvent(
                        event = HomeEvent.SaveAmount(amount = it.text.toDouble())
                    )
                }
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    )
}

@Composable
private fun ShowResult(homeState: HomeState) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Text(
            text = "Result",
            fontSize = 18.sp,
            color = Color.Black
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 10.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 50.dp)
                .background(color = colorResource(id = R.color.grey))
                .padding(start = 8.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = homeState.convertResult,
                fontSize = 18.sp,
                color = colorResource(id = R.color.greyDark)
            )
        }
    }
}