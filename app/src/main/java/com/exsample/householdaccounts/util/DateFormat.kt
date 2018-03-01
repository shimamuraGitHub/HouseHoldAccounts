package com.exsample.householdaccounts.util

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by ryosuke on 2018/02/11.
 */
val DATE_HAPHEN = "yyyy-MM-dd"

fun parseHyphen(yyyy_MM_dd:String) = SimpleDateFormat(DATE_HAPHEN).parse(yyyy_MM_dd)

fun createFirstDate(yyyy_MM: String) = parseHyphen("${yyyy_MM}-01")

fun createLastDate(yyyy_MM: String) :Date{
    val dates = yyyy_MM.split("-").map{it.toInt()}
    val lastDay = when(dates[1]){
        1, 3, 5, 7, 8, 10, 12 -> 31
        4, 6, 9, 11 -> 30
        2 -> if (dates[0] % 4 == 0) 29 else 28
        else -> 0
    }
    return parseHyphen("${yyyy_MM}-${lastDay}")
}

