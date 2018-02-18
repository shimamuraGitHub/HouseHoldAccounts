package com.exsample.householdaccounts.controller.widgets

import android.content.Context
import android.widget.EditText

/**
 * Created by ryosuke on 2018/02/10.
 */
fun EditText.isNullOrBlank() = text.isNullOrBlank()

fun EditText.getMoney() = text.toString().toInt()

fun EditText.clear() = text.clear()

fun EditText.isBlank() = length() == 0

fun EditText.append(char: CharSequence) = text.append(char)

fun EditText.length() = text.length

fun EditText.pop() {
    this.text = text.replace(length() - 1,length(),"")
}