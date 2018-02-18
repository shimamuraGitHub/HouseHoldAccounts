package com.exsample.householdaccounts.controller.message

/**
 * Created by ryosuke on 2018/02/17.
 */
class ConfirmMessage(message:String): Message(message=message) {
    val OK = "OK"
    val CANCEL = "キャンセル"
}