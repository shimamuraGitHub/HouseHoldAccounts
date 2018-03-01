package com.exsample.householdaccounts.controller.activity.config

import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.exsample.householdaccounts.R
import com.exsample.householdaccounts.controller.activity.navigation.NavigationListener
import kotlinx.android.synthetic.main.app_bar_config.*
import com.exsample.householdaccounts.controller.activity.find
import com.exsample.householdaccounts.controller.message.ConfirmMessage
import com.exsample.householdaccounts.controller.widgets.DialogBuilder
import com.exsample.householdaccounts.controller.widgets.setRecordTypeAdapter
import com.exsample.householdaccounts.controller.widgets.toEditableSelectedItem
import com.exsample.householdaccounts.db.DBOpenHelper
import com.exsample.householdaccounts.domain.RecordTypeList

class ConfigActivity : NavigationListener(){

    val dbHelper = DBOpenHelper(context = this,version = 1)
    lateinit var service : ConfigService

    lateinit var typeSpinner:Spinner
    lateinit var nameEdit:EditText
    lateinit var isExpenditure:Switch

    lateinit var registerNameEdit:EditText
    lateinit var registerIsExpenditure:Switch
    lateinit var registerButton:Button

    /* テーブルデータが更新されるたびに取得し直す */
    lateinit var recordTypes : RecordTypeList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)
        setSupportActionBar(toolbar)

        dbHelper.onCreate(dbHelper.writableDatabase)
        service = ConfigService(dbHelper)

        resetRecordTypes()

        setViews()

        fab.setOnClickListener { view ->
            val layout = getInflate()
            AlertDialog.Builder(this).setView(layout).show()
            findLayoutViews(layout)

            registerButton.setOnClickListener { register() }
        }

        setNavigation()
    }

    private fun getInflate() = LayoutInflater.from(this).inflate(R.layout.register_record_type,null)

    private fun findLayoutViews(layout: View){
        registerNameEdit = layout.find<EditText>(R.id.editText)
        registerIsExpenditure = layout.find<Switch>(R.id.switch1)
        registerButton = layout.find<Button>(R.id.register)
    }

    private fun register(){
        val message = service.register(registerNameEdit,isExpenditure)
        if(message.success){
            resetRecordTypes()
            Toast.makeText(this, message.message, Toast.LENGTH_LONG).show()
        }else{
            DialogBuilder(this).buildMessage(message).show()
        }
    }

    private fun setViews(){
        typeSpinner = find<Spinner>(R.id.spinner)
        nameEdit = find<EditText>(R.id.editText)
        isExpenditure = find<Switch>(R.id.switch1)
        setListener()
    }

    private fun resetRecordTypes(){
        recordTypes = service.findAllEnabled()
        typeSpinner.setRecordTypeAdapter(recordTypes)
    }

    private fun setListener(){
        typeSpinner.onItemSelectedListener = object:AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                nameEdit.text = typeSpinner.toEditableSelectedItem()
                isExpenditure.isChecked = recordTypes.findByNameSpinner(typeSpinner).isExpenditure!!
            }
        }
    }

    fun update(v:View){

        buildDialog({_,_->
            val from = recordTypes.findByNameSpinner(typeSpinner)

            val message = service.update(from,nameEdit,isExpenditure)

            if(message.success){
                resetRecordTypes()
                Toast.makeText(this, message.message, Toast.LENGTH_LONG).show()
            }else{
                DialogBuilder(this).buildMessage(message).show()
            }
        }).show()
    }

    fun erase(v: View){

        buildDialog({_,_->

            val message = service.erase(recordTypes.findByNameSpinner(typeSpinner))

            if(message.success){
                resetRecordTypes()
                Toast.makeText(this, message.message, Toast.LENGTH_LONG).show()
            }else{
                DialogBuilder(this).buildMessage(message).show()
            }
        }).show()
    }

    private fun buildDialog(listener:(DialogInterface, Int)-> Unit) = DialogBuilder(this)
            .buildConfirm(ConfirmMessage("よろしいですか？"),listener)
}
