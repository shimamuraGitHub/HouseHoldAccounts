package com.exsample.householdaccounts.controller.widgets

import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import com.exsample.householdaccounts.controller.message.ConfirmMessage
import com.exsample.householdaccounts.controller.message.ItemsMessage
import com.exsample.householdaccounts.controller.message.Message

/**
 * Created by ryosuke on 2018/02/10.
 */
class DialogBuilder(context: Context) {

    private val builder = AlertDialog.Builder(context)

    fun buildMessage(message: Message) :DialogBuilder{
        builder.setTitle(message.title)
                .setMessage(message.message)
                .setPositiveButton("OK",null)
        return this
    }

    fun buildConfirm(message:ConfirmMessage, listener: (DialogInterface, Int) -> Unit) :DialogBuilder{
        builder.setMessage(message.message)
                .setPositiveButton(message.OK, listener)
                .setNegativeButton(message.CANCEL, null)
        return this
    }

    fun buildItems(message: ItemsMessage, listener: (DialogInterface, Int) -> Unit):DialogBuilder{
        builder.setTitle(message.title)
                .setItems(message.items, listener)
        return this
    }

    fun show() = builder.show()
}