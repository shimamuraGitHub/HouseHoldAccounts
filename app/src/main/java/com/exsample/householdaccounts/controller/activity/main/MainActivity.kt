package com.exsample.householdaccounts.controller.activity.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.*
import com.exsample.householdaccounts.R
import com.exsample.householdaccounts.controller.activity.list.ListActivity
import com.exsample.householdaccounts.controller.widgets.ButtonView
import com.exsample.householdaccounts.controller.widgets.AlertDialog
import com.exsample.householdaccounts.controller.widgets.EditTextView
import com.exsample.householdaccounts.controller.widgets.SpinnerView
import com.exsample.householdaccounts.db.DBOpenHelper

class MainActivity : AppCompatActivity() {

    val dbHelper = DBOpenHelper(context = this,version = 1)
    lateinit var service: MainService

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper.onCreate(dbHelper.writableDatabase)
        service = MainService(dbHelper)

        val spinner = SpinnerView(findViewById<Spinner>(R.id.itemNames))
        spinner.buildRecordTypeAdapter(service.findRecordTypes())
    }

    /**
     * 登録ボタン押下時の処理.
     *
     */
    fun register(v: View){

        val moneyEditText = EditTextView(findViewById<EditText>(R.id.moneyAmount))
        val recordTypeSpinner = SpinnerView(findViewById<Spinner>(R.id.itemNames))

        val message = service.registerRecord(moneyEditText,recordTypeSpinner)

        if(message.success){
            moneyEditText.clear()
            Toast.makeText(this, message.message, Toast.LENGTH_LONG).show()
            return
        }

        val alertDialog = AlertDialog(this)
        alertDialog.build(message)
        alertDialog.builder.show()
    }

    /**
     * 各数字ボタン押下時の処理.
     * <p>
     * 金額テキストに数字が入力される
     */
    fun input(v: View) {

        val numberButton = ButtonView(v as Button);
        val moneyEditText = EditTextView(findViewById<EditText>(R.id.moneyAmount))

        // テキストの1文字目に0が入る事を阻止する
        if (numberButton.isZero() && moneyEditText.isBlank()) {
            return
        }

        moneyEditText.append(numberButton.text())
    }

    /**
     * ◀×ボタン押下処理.
     *
     */
    fun pop(v: View) {

        val moneyAmount = EditTextView(findViewById<EditText>(R.id.moneyAmount))

        if(moneyAmount.isBlank()) {
            return
        }

        moneyAmount.pop()
    }

    /**
     * クリアボタン押下処理.
     */
    fun clear(v:View) = EditTextView(findViewById<EditText>(R.id.moneyAmount)).clear()

    fun toList(v:View?){
        val intent = Intent(this, ListActivity::class.java)
        startActivityForResult(intent,1)
    }
}
