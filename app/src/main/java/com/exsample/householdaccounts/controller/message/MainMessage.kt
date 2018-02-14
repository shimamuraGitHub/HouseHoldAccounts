package com.exsample.householdaccounts.controller.message

import com.exsample.householdaccounts.domain.Record
import com.exsample.householdaccounts.domain.RecordType

/**
 * Created by ryosuke on 2018/02/10.
 */
class MainMessage(success:Boolean, title:String? = null, message:String? = null) : Message(success,title,message){

    fun buildRegisterMessage(record:Record, type: RecordType){
        message = """
                家計簿に記入しました。
                ${type.name}：${record.money} 円
            """.trimIndent()
    }
}