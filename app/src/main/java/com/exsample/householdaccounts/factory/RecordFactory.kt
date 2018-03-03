package com.exsample.householdaccounts.factory

import android.widget.EditText
import android.widget.Spinner
import com.exsample.householdaccounts.controller.widgets.DateSpinnerFunctions
import com.exsample.householdaccounts.controller.widgets.getMoney
import com.exsample.householdaccounts.controller.widgets.isNullOrBlank
import com.exsample.householdaccounts.controller.widgets.toStringSelectedItem
import com.exsample.householdaccounts.domain.Record
import com.exsample.householdaccounts.domain.RecordType
import java.util.*

/**
 * Created by ryosuke on 2018/02/10.
 */
class RecordFactory():DateSpinnerFunctions{

    fun create(money: EditText, type: RecordType, date: Spinner)
            = Record( UUID.randomUUID().toString(), date.selectedDate(), type.code, money.getMoney())

    fun create(date: Date, recordType: RecordType, moneyEdit: EditText) : Record{
        var money:Int? = null
        if(!moneyEdit.isNullOrBlank()){
            money = moneyEdit.getMoney()
        }
        return Record(date=date,type=recordType.code,money=money)
    }
}
