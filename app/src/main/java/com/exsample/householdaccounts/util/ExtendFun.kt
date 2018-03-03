package com.exsample.householdaccounts.util

import android.text.SpannableStringBuilder
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by ryosuke on 2018/02/24.
 */
fun Boolean.toInt() = if (this) 1 else 0

fun Int.toBoolean() = (this == 1)

fun String.toEditable() = SpannableStringBuilder(this)

val DATE_HYPHEN = """[\d]{4}-[\d]{2}-[\d]{2}""".toRegex()
val SQL_TIMESTAMP_HYPHEN = """${DATE_HYPHEN} [\d]{2}:[\d]{2}:[\d]{2}.[\d]{1,3}""".toRegex()

fun String.toDate() = SimpleDateFormat(getFormat()).parse(this)

private fun String.getFormat() = when {
        this.matches(DATE_HYPHEN) -> "yyyy-MM-dd"
        this.matches(SQL_TIMESTAMP_HYPHEN) -> "yyyy-MM-dd hh:mm:ss.S"

        else -> ""
    }

fun Int.toCode(digit:Int): String {
    var codeBuilder = this.toString()
    var count = digit - codeBuilder.length
    while(count-- > 0){
        codeBuilder = "0${codeBuilder}"
    }
    return codeBuilder
}

fun Date.toTimestamp() = Timestamp(this.time)

fun Calendar.getYear() = this.get(Calendar.YEAR)

fun Calendar.getMonth() = this.get(Calendar.MONTH)

fun Calendar.getDayOfMonth() = this.get(Calendar.DAY_OF_MONTH)

fun Calendar.isLeapYear() = this.getYear() % 4 == 0

fun findLastDay(year:Int,month:Int):Int{
    val lastDay = when(month){
        1, 3, 5, 7, 8, 10, 12 -> 31
        4, 6, 9, 11 -> 30
        2 -> if (year % 4 == 0) 29 else 28
        else -> 0
    }
    return lastDay
}

