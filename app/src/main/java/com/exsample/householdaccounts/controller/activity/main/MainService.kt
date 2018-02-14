package com.exsample.householdaccounts.controller.activity.main

import com.exsample.householdaccounts.controller.factory.RecordFactory
import com.exsample.householdaccounts.controller.message.ErrorMessage
import com.exsample.householdaccounts.controller.message.MainMessage
import com.exsample.householdaccounts.controller.message.Message
import com.exsample.householdaccounts.controller.widgets.EditTextView
import com.exsample.householdaccounts.controller.widgets.SpinnerView
import com.exsample.householdaccounts.db.DBOpenHelper
import com.exsample.householdaccounts.domain.RecordAgent
import com.exsample.householdaccounts.domain.RecordTypeList

/**
 * Created by ryosuke on 2018/02/10.
 */
class MainService(helper: DBOpenHelper){

    val recordAgent = RecordAgent(helper)

    val recordFactory = RecordFactory()

    fun registerRecord(moneyEditText: EditTextView, recordTypeSpinner: SpinnerView) : Message {

        if(moneyEditText.isNullOrBlank()){
            return ErrorMessage("金額が入力されていません")
        }

        val typeList = RecordTypeList(recordAgent.findAllTypes())
        val selectedType = typeList.findByNameSpinner(recordTypeSpinner)

        val record = recordFactory.create(moneyEditText,selectedType)

        if(recordAgent.register(record) == 1){
            val message = MainMessage(true)
            message.buildRegisterMessage(record,selectedType)
            return message
        }
        return ErrorMessage("登録に失敗しました。")
    }

    fun findRecordTypes() = recordAgent.findAllTypes()
}