package com.exsample.householdaccounts.controller.factory

import android.widget.EditText
import android.widget.Switch
import com.exsample.householdaccounts.controller.widgets.text
import com.exsample.householdaccounts.domain.RecordType
import java.util.*

/**
 * Created by ryosuke on 2018/02/21.
 */
class RecordTypeFactoy{

    fun create(name: EditText, code: String, expenditure: Switch)
         = RecordType(code, name.text(), expenditure.isChecked, true, Date())
}