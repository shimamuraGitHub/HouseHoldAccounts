package com.exsample.householdaccounts.controller.activity.config

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.*
import com.exsample.householdaccounts.controller.message.ConfirmMessage
import com.exsample.householdaccounts.controller.widgets.DialogBuilder

class ConfigActivity:AbstractActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setNavigation()
    }

    override fun register(){
        val message = service.register(registerNameEdit,isExpenditure)
        if(message.success){
            resetRecordTypes()
            Toast.makeText(this, message.message, Toast.LENGTH_LONG).show()
        }else{
            DialogBuilder(this).buildMessage(message).show()
        }
    }

    fun update(v:View){

        buildDialog({_,_->
            val from = recordTypes.findBySelectedName(typeSpinner)

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

            val message = service.erase(recordTypes.findBySelectedName(typeSpinner))

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
