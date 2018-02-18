package com.exsample.householdaccounts.controller.activity.main

import android.widget.EditText
import android.widget.Spinner
import com.exsample.householdaccounts.controller.factory.RecordFactory
import com.exsample.householdaccounts.controller.message.ResultMessage
import com.exsample.householdaccounts.controller.message.Message
import com.exsample.householdaccounts.controller.widgets.isNullOrBlank
import com.exsample.householdaccounts.db.DBOpenHelper
import com.exsample.householdaccounts.domain.Record
import com.exsample.householdaccounts.domain.RecordAgent
import com.exsample.householdaccounts.domain.RecordType
import com.exsample.householdaccounts.domain.RecordTypeList

/**
 * Created by ryosuke on 2018/02/10.
 */
class MainService(helper: DBOpenHelper){

    val recordAgent = RecordAgent(helper)

    val recordFactory = RecordFactory()

    fun registerRecord(moneyEdit: EditText, typeSpinner: Spinner) : ResultMessage {

        if(moneyEdit.isNullOrBlank()){
            return ResultMessage(false,"ERROR","金額が入力されていません")
        }

        val typeList = recordAgent.findAllTypes()
        val selectedType = typeList.findByNameSpinner(typeSpinner)

        val record = recordFactory.create(moneyEdit,selectedType)

        if(recordAgent.register(record) == 1){
            return ResultMessage(true,message=buildRegisterMessage(record,selectedType))
        }
        return ResultMessage(false,"ERROR","登録に失敗しました。")
    }

    private fun buildRegisterMessage(record: Record, type: RecordType) = """
                家計簿に記入しました。
                ${type.name}：${record.money} 円
            """.trimIndent()

    fun findRecordTypes() = recordAgent.findAllTypes()
}