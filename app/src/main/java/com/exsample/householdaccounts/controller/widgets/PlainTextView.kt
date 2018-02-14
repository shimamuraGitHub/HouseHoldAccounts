package com.exsample.householdaccounts.controller.widgets

import android.content.Context
import android.widget.TextView

/**
 * Created by ryosuke on 2018/02/10.
 */
open class PlainTextView{
    val textView: TextView

    constructor(context: Context){
        textView = TextView(context)
    }

    constructor(textView: TextView){
        this.textView = textView
    }

}