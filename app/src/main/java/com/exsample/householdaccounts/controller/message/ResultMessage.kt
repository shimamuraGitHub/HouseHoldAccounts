package com.exsample.householdaccounts.controller.message

/**
 * Created by ryosuke on 2018/02/10.
 */
class ResultMessage(val success:Boolean,title:String? = null,message:String? = null) : Message(title,message)