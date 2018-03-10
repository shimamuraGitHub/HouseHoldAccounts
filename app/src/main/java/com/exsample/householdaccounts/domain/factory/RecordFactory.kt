package com.exsample.householdaccounts.domain.factory

import android.database.Cursor
import android.widget.EditText
import android.widget.Spinner
import com.exsample.householdaccounts.controller.widgets.DateSpinnerFunctions
import com.exsample.householdaccounts.controller.widgets.getMoney
import com.exsample.householdaccounts.domain.record.Record
import com.exsample.householdaccounts.domain.type.RecordType
import com.exsample.householdaccounts.util.toDate
import java.util.*

/**
 * Created by ryosuke on 2018/02/10.
 */
object RecordFactory:DateSpinnerFunctions{

    fun create(money: EditText, type: RecordType, date: Spinner)
        = Record(UUID.randomUUID().toString(), date.selectedDate(), type.code, money.getMoney(), Date(), Date())

    fun create(date: Date, recordType: RecordType, moneyEdit: EditText) : Record {

        val record = Record(date = date, type = recordType.code, money = moneyEdit.getMoney())
        record.recordType = recordType

        return record
    }

    fun create(record: Record, date:Spinner, type: RecordType, money: EditText)
        = Record(record.id, date.selectedDate(), type.code, money.getMoney(), updatedAt = Date())

    fun create(cursor:Cursor)
        = Record(
            cursor.getString(0),                          // ID
            cursor.getString(1).toDate(),                 // DATE
            cursor.getString(2),                          // TYPE_CODE
            cursor.getInt(3),                              // MONEY
            cursor.getString(4)?.toDate(),
            cursor.getString(5)?.toDate()
    )

}
