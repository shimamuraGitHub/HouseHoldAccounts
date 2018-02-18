package com.exsample.householdaccounts.controller.factory

import android.widget.EditText
import android.widget.Spinner
import com.exsample.householdaccounts.controller.widgets.getMoney
import com.exsample.householdaccounts.controller.widgets.isNullOrBlank
import com.exsample.householdaccounts.domain.Record
import com.exsample.householdaccounts.domain.RecordType
import java.util.*

/**
 * Created by ryosuke on 2018/02/10.
 */
class RecordFactory(){

    fun create(moneyEdit: EditText, recordType: RecordType)
            = Record( UUID.randomUUID().toString(), Date(), recordType.code, moneyEdit.getMoney())

    fun create(dateSpinner: Spinner, body:(String)->Date, recordType: RecordType, moneyEdit: EditText) : Record{
        val date = body(dateSpinner.selectedItem.toString())
        var money:Int? = null
        if(!moneyEdit.isNullOrBlank()){
            money = moneyEdit.getMoney()
        }
        return Record(date=date,type=recordType.code,money=money)
    }
}
