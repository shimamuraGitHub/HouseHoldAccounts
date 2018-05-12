package com.exsample.householdaccounts.controller.activity

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.exsample.householdaccounts.R

/**
 * Created by ryosuke on 2018/02/17.
 */
fun <T : View?> Activity.find(id: Int) = this.findViewById<T>(id)

fun <T : View?> View.find(id: Int) = this.findViewById<T>(id)

fun Activity.getInflate(id: Int) = LayoutInflater.from(this).inflate(id, null)

// 自分の親との関係を切り離す
fun View.removeFromParent(){
    if(parent != null){
        (parent as ViewGroup).removeView(this)
    }
}
