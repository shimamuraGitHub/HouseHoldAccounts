package com.exsample.householdaccounts.controller.activity.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import com.exsample.householdaccounts.R
import com.exsample.householdaccounts.controller.activity.find
import com.exsample.householdaccounts.controller.activity.list.ListActivity
import com.exsample.householdaccounts.controller.activity.navigation.NavigationListener
import com.exsample.householdaccounts.controller.widgets.*
import com.exsample.householdaccounts.db.DBOpenHelper

class MainActivity : NavigationListener() {

    val dbHelper = DBOpenHelper(context = this,version = 1)
    lateinit var service: MainService

    lateinit var typeSpinner:Spinner
    lateinit var moneyEdit: EditText

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper.onCreate(dbHelper.writableDatabase)
        service = MainService(dbHelper)

        findViews()

        typeSpinner.setRecordTypeAdapter(service.findRecordTypes())

        setNavigation()
    }

    private fun findViews(){
        typeSpinner = find<Spinner>(R.id.typeNames)
        moneyEdit = find<EditText>(R.id.moneyEdit)
    }

    /**
     * 登録ボタン押下時の処理.
     *
     */
    fun register(v: View){

        val message = service.registerRecord(moneyEdit,typeSpinner)

        if(message.success){
            moneyEdit.clear()
            Toast.makeText(this, message.message, Toast.LENGTH_LONG).show()
            return
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
     *
     */
    fun pop(v: View)  = service.popMoneyEdit(moneyEdit)

    /**
     * クリアボタン押下処理.
     */
    fun clear(v:View) = moneyEdit.clear()

    fun toList(v:View?){
        val intent = Intent(this, ListActivity::class.java)
        startActivityForResult(intent,1)
    }
}
