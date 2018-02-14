package com.exsample.householdaccounts.controller.factory

import com.exsample.householdaccounts.controller.widgets.EditTextView
import com.exsample.householdaccounts.controller.widgets.SpinnerView
import com.exsample.householdaccounts.domain.Record
import com.exsample.householdaccounts.domain.RecordType
import com.exsample.householdaccounts.util.createFirstDate
import com.exsample.householdaccounts.util.createLastDate
import java.util.*

/**
 * Created by ryosuke on 2018/02/10.
 */
class RecordFactory(){
    fun create(moneyEditText: EditTextView, recordType: RecordType)
            = Record( UUID.randomUUID().toString(), Date(), recordType.code,moneyEditText.getMoney())

    fun create(dateSpinner: SpinnerView ,body:(String)->Date,recordType: RecordType,moneyEdit: EditTextView) : Record{
        val date = body(dateSpinner.selectedItem().toString())
        val money = moneyEdit.getMoney()
        return Record(date=date,type=recordType.code,money=money)
    }
}
