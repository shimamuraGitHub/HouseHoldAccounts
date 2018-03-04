package com.exsample.householdaccounts.controller.activity.config

import android.widget.EditText
import android.widget.Switch
import com.exsample.householdaccounts.factory.RecordTypeFactoy
import com.exsample.householdaccounts.controller.message.ResultMessage
import com.exsample.householdaccounts.controller.widgets.text
import com.exsample.householdaccounts.db.DBOpenHelper
import com.exsample.householdaccounts.domain.RecordAgent
import com.exsample.householdaccounts.domain.RecordType

/**
 * Created by ryosuke on 2018/02/21.
 */
class ConfigService(helper: DBOpenHelper) {

    val recordAgent = RecordAgent(helper)

    val recordTypeFactory = RecordTypeFactoy()

    fun findAllEnabled() = recordAgent.findAllEnabledTypes()

    fun register(name:EditText,isExpenditure: Switch): ResultMessage {

        val recordTypes = findAllEnabled()

        if(recordTypes.existsEqualsName(name)){
            return createMessage(false, "項目名「${name.text()}」は既に設定されています。")
        }

        val code = recordTypes.findNotExistsMinCode()

        val target = recordTypeFactory.create(name,code,isExpenditure)

        if(recordAgent.registerType(target) != -1){
            return createMessage(true, "${target.name}の登録に成功しました。")
        }
        return createMessage(false, "${target.name}の登録に失敗しました。")
    }

    fun update(from: RecordType, rename:EditText, isExpenditure:Switch): ResultMessage {

        val target = from.copy(name= rename.text(),isExpenditure = isExpenditure.isChecked)

        val recordTypes = recordAgent.findAllEnabledTypes()

        if(recordTypes.hasNameWithDifferCode(target)){
            return createMessage(false, "項目名「${target.name}」は既に設定されています。")
        }

        if(recordAgent.updateType(target) == 1){
            return createMessage(true, "${target.name}の更新に成功しました。")
        }
        return createMessage(false, "${target.name}の更新に失敗しました。")
    }

    fun erase(target : RecordType) :ResultMessage{

        if(recordAgent.toDisableType(target) == 1){
            return createMessage(true, "${target.name}の削除に成功しました。")
        }
        return createMessage(false,"${target.name}の削除に失敗しました。")
    }

    private fun createMessage(success:Boolean, message:String)
            = ResultMessage(success = success, message = message)
}