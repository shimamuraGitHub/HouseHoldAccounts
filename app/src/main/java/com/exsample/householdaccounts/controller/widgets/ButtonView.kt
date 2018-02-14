package com.exsample.householdaccounts.controller.widgets

import android.content.Context
import android.widget.Button

/**
 * Created by ryosuke on 2018/02/10.
 */
class ButtonView{

    val button:Button

    constructor(context: Context) {
        button = Button(context)
    }

    constructor(button:Button){
        this.button = button
    }

    fun isZero() = button.text.equals("0")

    fun text() = button.text
}