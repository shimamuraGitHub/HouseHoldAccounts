package com.exsample.householdaccounts.controller.widgets

import android.content.Context
import android.support.v7.app.AlertDialog
import com.exsample.householdaccounts.controller.message.Message

/**
 * Created by ryosuke on 2018/02/10.
 */
class AlertDialog(context: Context) {

    val builder = AlertDialog.Builder(context)

    fun build(message: Message) {
        builder.setTitle(message.title)
                .setMessage(message.message)
                .setPositiveButton("OK",null)
    }
}