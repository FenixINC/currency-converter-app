package com.example.currency_converter_app.data.database

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.reflect.Type

class CurrencyLocalDataSource(
    context: Context
) {
    private val sharedPreferences = context.getSharedPreferences(
        "history_preferences",
        Context.MODE_PRIVATE
    )

    fun saveToHistory(list: List<HistoryModel>) {
        val gson = Gson()
        val type: Type = object : TypeToken<ArrayList<HistoryModel?>?>() {}.type
        val jsonResult = sharedPreferences.getString("history_data", null)
        if (jsonResult != null) {
            val historyList = gson.fromJson<Any>(jsonResult, type) as ArrayList<HistoryModel>

            sharedPreferences.edit().apply {
                historyList.addAll(elements = list)
                val json = gson.toJson(historyList)
                putString("history_data", json)
                commit()
            }
        } else {
            sharedPreferences.edit().apply {
                val json = gson.toJson(list)
                putString("history_data", json)
                commit()
            }
        }
    }

    fun getHistory(): Flow<List<HistoryModel>> = flow {
        val gson = Gson()
        val type: Type = object : TypeToken<ArrayList<HistoryModel?>?>() {}.type
        val json = sharedPreferences.getString("history_data", null)
        kotlin.runCatching {
            gson.fromJson<Any>(json, type) as ArrayList<HistoryModel>
        }.onSuccess { historyList ->
            emit(value = historyList)
        }.onFailure {
            emit(value = emptyList())
        }
    }
}