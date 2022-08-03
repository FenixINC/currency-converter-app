package com.example.currency_converter_app.extention

import java.math.RoundingMode
import java.text.DecimalFormat

fun Double.formatNumber(): String {
    val df = DecimalFormat("#.##")
    df.roundingMode = RoundingMode.DOWN
    return df.format(this).toString()
}