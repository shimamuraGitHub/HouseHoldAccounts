package com.exsample.householdaccounts.controller.widgets

import android.content.Context
import android.widget.EditText

/**
 * Created by ryosuke on 2018/02/10.
 */
class EditTextView {

    val editText:EditText

    constructor(context: Context){
        editText = EditText(context)
    }

    constructor(editText: EditText){
        this.editText = editText
    }

    fun text() = editText.text

    fun isNullOrBlank() = text().isNullOrBlank()

    fun getMoney() = text().toString().toInt()

    fun clear() = text().clear()

    fun isBlank() = length() == 0

    fun append(char: CharSequence) = text().append(char)

    fun length() = text().length

    fun pop() {
        editText.text = text().replace(length() - 1,length(),"")
    }
}