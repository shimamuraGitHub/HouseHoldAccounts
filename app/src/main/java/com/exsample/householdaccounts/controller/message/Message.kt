package com.exsample.householdaccounts.controller.message

/**
 * Created by ryosuke on 2018/02/10.
 */
abstract class Message(
        val success:Boolean,
        val title:String?,
        var message:String?
)