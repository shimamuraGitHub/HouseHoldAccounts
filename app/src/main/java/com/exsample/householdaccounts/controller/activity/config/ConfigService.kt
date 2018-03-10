package com.exsample.householdaccounts.controller.activity.config

import android.widget.EditText
import android.widget.Spinner
import android.widget.Switch
import com.exsample.householdaccounts.domain.factory.RecordTypeFactory
import com.exsample.householdaccounts.controller.message.ResultMessage
import com.exsample.householdaccounts.controller.widgets.text
import com.exsample.householdaccounts.db.DBOpenHelper
import com.exsample.householdaccounts.domain.type.RecordType
import com.exsample.householdaccounts.domain.type.RecordTypeAgent
import com.exsample.householdaccounts.domain.type.RecordTypeList

/**
 * Created by ryosuke on 2018/02/21.
 */
class ConfigService(helper: DBOpenHelper) {

    private val typeAgent = RecordTypeAgent(helper)

    fun findAllEnabled() = typeAgent.findAllEnabledTypes()

    fun findType(recordTypeList: RecordTypeList, nameSpinner: Spinner)
         = recordTypeList.findByName(RecordTypeFactory.create(nameSpinner))

    fun register(name:EditText,isExpenditure: Switch): ResultMessage {

        val recordTypes = findAllEnabled()

        if(recordTypes.existsEqualsName(RecordTypeFactory.create(name))){
            return createMessage(false, "項目名「${name.text()}」は既に設定されています。")
        }

        val code = recordTypes.findNotExistsMinCode()

        val target = RecordTypeFactory.create(name,code,isExpenditure)

        if(typeAgent.registerType(target) != -1){
            return createMessage(true, "${target.name}の登録に成功しました。")
        }
        return createMessage(false, "${target.name}の登録に失敗しました。")
    }

    fun update(from: RecordType, rename:EditText, isExpenditure:Switch): ResultMessage {

        val target = from.copy(name= rename.text(),isExpenditure = isExpenditure.isChecked)

        val recordTypes = typeAgent.findAllEnabledTypes()

        if(recordTypes.hasNameWithDifferCode(target)){
            return createMessage(false, "項目名「${target.name}」は既に設定されています。")
        }

        if(typeAgent.updateType(target) == 1){
            return createMessage(true, "${target.name}の更新に成功しました。")
        }
        return createMessage(false, "${target.name}の更新に失敗しました。")
    }

    fun erase(recordTypeList: RecordTypeList, typeSpinner:Spinner) :ResultMessage{

        val target = recordTypeList.findByName(RecordTypeFactory.create(typeSpinner))

        if(typeAgent.toDisableType(target) == 1){
            return createMessage(true, "${target.name}の削除に成功しました。")
        }
        return createMessage(false,"${target.name}の削除に失敗しました。")
    }

    private fun createMessage(success:Boolean, message:String)
            = ResultMessage(success = success, message = message)
}