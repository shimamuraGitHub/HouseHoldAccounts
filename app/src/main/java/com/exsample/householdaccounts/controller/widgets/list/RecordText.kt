package com.exsample.householdaccounts.controller.widgets.view.list

import android.widget.TextView
import com.exsample.householdaccounts.controller.widgets.PlainTextView

/**
 * Created by ryosuke on 2018/02/10.
 */
class RecordText: PlainTextView {

    constructor(textView: TextView, text: String, gravity: Int? = null):super(textView){
        textView.text = text
        textView.textSize = 20.0F
        textView.setPadding(20,0,20,0)
        if(gravity != null) textView.gravity = gravity
    }

}