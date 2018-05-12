package com.exsample.householdaccounts.controller.activity.main

import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import com.exsample.householdaccounts.domain.factory.RecordFactory
import com.exsample.householdaccounts.controller.message.ResultMessage
import com.exsample.householdaccounts.controller.widgets.*
import com.exsample.householdaccounts.db.DBOpenHelper
import com.exsample.householdaccounts.domain.record.Record
import com.exsample.householdaccounts.domain.record.RecordAgent
import com.exsample.householdaccounts.domain.type.RecordType
import com.exsample.householdaccounts.domain.factory.RecordTypeFactory
import com.exsample.householdaccounts.domain.type.RecordTypeAgent

/**
 * Created by ryosuke on 2018/02/10.
 */
class MainService(helper: DBOpenHelper):DateSpinnerFunctions{

    private val recordAgent = RecordAgent(helper)
    private val typeAgent = RecordTypeAgent(helper)

    fun register(moneyEdit: EditText, typeSpinner: Spinner, dateSpinner: Spinner) : ResultMessage {

        if(moneyEdit.isNullOrBlank()){
            return ResultMessage(false,"ERROR","金額が入力されていません")
        }

        val typeList = typeAgent.findAllEnabledTypes()
        val selectedType = typeList.findByName(RecordTypeFactory.create(typeSpinner))

        val record = RecordFactory.create(moneyEdit,selectedType,dateSpinner)

        if(recordAgent.register(record) == 1){
            return ResultMessage(true,message=buildRegisterMessage(record,selectedType))
        }
        return ResultMessage(false,"ERROR","登録に失敗しました。")
    }

    private fun buildRegisterMessage(target: Record, targetType: RecordType) = """
                家計簿に記入しました。
                ${targetType.name}：${target.money} 円
            """.trimIndent()

    fun findRecordTypes() = typeAgent.findAllEnabledTypes()

    fun inputNumber(numberButton:Button,moneyEdit: EditText){
        // テキストの1文字目に0が入る事を阻止する
        if (numberButton.isZero() && moneyEdit.isBlank()) {
            return
        }
        moneyEdit.append(numberButton.text)
    }

    fun update(target: Record, dateSpinner: Spinner, moneyEdit: EditText, typeSpinner: Spinner):ResultMessage{
        val types = typeAgent.findAllEnabledTypes()
        val selectedType = types.findByName(RecordTypeFactory.create(typeSpinner))
        val changed = RecordFactory.create(target,dateSpinner,selectedType,moneyEdit)

        if(recordAgent.update(changed) == 1){
            return ResultMessage(true,message="更新に成功しました。")
        }
        return ResultMessage(false,"ERROR","更新に失敗しました。")
    }
}