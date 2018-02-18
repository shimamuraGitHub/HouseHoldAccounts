package com.exsample.householdaccounts.controller.activity

import android.app.Activity
import android.view.LayoutInflater
import android.view.View

/**
 * Created by ryosuke on 2018/02/17.
 */
fun <T : View?> Activity.find(id:Int) = this.findViewById<T>(id)

fun <T : View?> View.find(id:Int) = this.findViewById<T>(id)

