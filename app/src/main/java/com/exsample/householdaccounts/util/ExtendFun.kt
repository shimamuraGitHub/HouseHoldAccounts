package com.exsample.householdaccounts.util

import android.text.SpannableStringBuilder
import java.sql.Timestamp
import java.util.*

/**
 * Created by ryosuke on 2018/02/24.
 */
fun Boolean.toInt() = if (this) 1 else 0

fun Int.toBoolean() = (this == 1)

fun String.toEditable() = SpannableStringBuilder(this)

fun Int.toCode(digit:Int): String {
    var codeBuilder = this.toString()
    var count = digit - codeBuilder.length
    while(count-- > 0){
        codeBuilder = "0${codeBuilder}"
    }
    return codeBuilder
}

fun Date.toTimestampString() = Timestamp(this.time).toString()