package com.exsample.householdaccounts.controller.widgets.view.list

import android.view.Gravity
import android.widget.TextView
import com.exsample.householdaccounts.controller.widgets.PlainTextView

/**
 * Created by ryosuke on 2018/02/10.
 */
class IndexText(textView: TextView,text:String): PlainTextView(textView) {

    init{
        textView.text = text
        textView.gravity = Gravity.CENTER
        textView.textSize = 30.0F
        textView.setPadding(20,0,20,0)
    }

}