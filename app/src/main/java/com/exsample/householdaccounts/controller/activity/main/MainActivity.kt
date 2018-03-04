package com.exsample.householdaccounts.controller.activity.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import com.exsample.householdaccounts.controller.activity.list.ListActivity
import com.exsample.householdaccounts.controller.widgets.*
import com.exsample.householdaccounts.domain.Record

class MainActivity : AbstractActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setNavigation()
    }

    /**
     * 登録ボタン押下時の処理.
     *
     */
    fun register(v: View){

        val message = service.register(moneyEdit,typeSpinner,dateSpinner)

        if(message.success){
            moneyEdit.clear()
            Toast.makeText(this, message.message, Toast.LENGTH_LONG).show()
            return
        }
        DialogBuilder(this).buildMessage(message).show()
    }

    override fun update(target: Record) {
        val message = service.update(target,dateSpinner,moneyEdit, typeSpinner)
        if(message.success){
            moneyEdit.clear()
            Toast.makeText(this, message.message, Toast.LENGTH_LONG).show()
            startActivityForResult(Intent(this, ListActivity::class.java),1)
        }
        DialogBuilder(this).buildMessage(message).show()
    }

    /**
     * 各数字ボタン押下時の処理.
     * <p>
     * 金額テキストに数字が入力される
     */
    fun input(v: View) = service.inputNumber(v as Button, moneyEdit)

    /**
     * ◀×ボタン押下処理.
     */
    fun pop(v: View)  = moneyEdit.pop()

    /**
     * クリアボタン押下処理.
     */
    fun clear(v:View) = moneyEdit.clear()

    fun toList(v:View?) = startActivityForResult(Intent(this, ListActivity::class.java),1)
}
