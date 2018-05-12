package com.exsample.householdaccounts.domain.factory

import android.database.Cursor
import android.widget.EditText
import android.widget.Spinner
import android.widget.Switch
import com.exsample.householdaccounts.controller.widgets.text
import com.exsample.householdaccounts.domain.type.RecordType
import com.exsample.householdaccounts.util.getBoolean
import com.exsample.householdaccounts.util.getDate
import java.util.*

/**
 * Created by ryosuke on 2018/02/21.
 */
object RecordTypeFactory{

    fun create(name: EditText, code: String, isExpenditure: Switch)
        = RecordType(
            code,
            name.text(),
            isExpenditure.isChecked,
            true,
            Date()
        )

    fun create(names: Spinner) = RecordType(name = names.selectedItem.toString())

    fun create(nameEdit:EditText) = RecordType(name = nameEdit.text())

    fun create(cursor: Cursor) = RecordType(
            cursor.getString(0),                             // CODE
            cursor.getString(1),                             // NAME
            cursor.getBoolean(2),                            // IS_EXPENDITURE
            cursor.getBoolean(3),                            // ENABLED
            cursor.getDate(4),                               // AT_STARTED
            cursor.getDate(5)                                // AT_ENDED
    )
}