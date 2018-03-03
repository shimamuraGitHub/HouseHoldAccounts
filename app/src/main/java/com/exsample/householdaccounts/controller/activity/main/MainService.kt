package com.exsample.householdaccounts.controller.activity.main

import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import com.exsample.householdaccounts.factory.RecordFactory
import com.exsample.householdaccounts.controller.message.ResultMessage
import com.exsample.householdaccounts.controller.widgets.isBlank
import com.exsample.householdaccounts.controller.widgets.isNullOrBlank
import com.exsample.householdaccounts.controller.widgets.isZero
import com.exsample.householdaccounts.controller.widgets.pop
import com.exsample.householdaccounts.db.DBOpenHelper
import com.exsample.householdaccounts.domain.Record
import com.exsample.householdaccounts.domain.RecordAgent
import com.exsample.householdaccounts.domain.RecordType

/**
 * Created by ryosuke on 2018/02/10.
 */
class MainService(helper: DBOpenHelper){

    val recordAgent = RecordAgent(helper)

    val recordFactory = RecordFactory()

    fun registerRecord(moneyEdit: EditText, typeSpinner: Spinner,dateSpinner: Spinner) : ResultMessage {

        if(moneyEdit.isNullOrBlank()){
            return ResultMessage(false,"ERROR","金額が入力されていません")
        }

        val typeList = recordAgent.findAllEnabledTypes()
        val selectedType = typeList.findByNameSpinner(typeSpinner)

        val record = recordFactory.create(moneyEdit,selectedType,dateSpinner)

        if(recordAgent.register(record) == 1){
            return ResultMessage(true,message=buildRegisterMessage(record,selectedType))
        }
        return ResultMessage(false,"ERROR","登録に失敗しました。")
    }

    private fun buildRegisterMessage(target: Record, targetType: RecordType) = """
                家計簿に記入しました。
                ${targetType.name}：${target.money} 円
            """.trimIndent()

    fun findRecordTypes() = recordAgent.findAllEnabledTypes()

    fun inputNumber(numberButton:Button,moneyEdit: EditText){
        // テキストの1文字目に0が入る事を阻止する
        if (numberButton.isZero() && moneyEdit.isBlank()) {
            return
        }
        moneyEdit.append(numberButton.text)
    }
}