package com.exsample.householdaccounts.domain

import android.widget.EditText
import android.widget.Spinner
import com.exsample.householdaccounts.controller.widgets.text
import com.exsample.householdaccounts.util.existsNext
import com.exsample.householdaccounts.util.toCode

/**
 * Created by ryosuke on 2018/02/11.
 */
class RecordTypeList(val list:List<RecordType>) {

    fun<T> map(func:(RecordType) -> T) = list.map(func)

    fun findLatest(record: Record) = list
            .filter { it.name!! == record.recordType!!.name }
            .sortedBy { it.atStarted!!.time }
            .reversed()
            .first()

    fun findByRecord(record: Record) = list
            .filter { it.code.equals(record.type) }
            .filter{it.atStartedEqualsOrBefore(record)}
            .first {if(it.atEnded != null) it.atEndedAfter(record) else true}

    fun findBySelectedName(names: Spinner) = list.first { it.name!! == names.selectedItem }

    fun existsEqualsName(edit: EditText) = list.any { it.name!! == edit.text() }

    fun hasNameWithDifferCode(type: RecordType) = list.any { it.equalsNameWithDifferCode(type) }

    fun findNotExistsMinCode() : String {
        val intCodes = list.map { it.code!!.toInt() }
        return (intCodes.sorted().first { !intCodes.existsNext(it) } + 1).toCode(3)
    }

    fun indexOfByCode(record: Record) = list.indexOfFirst { it.code.equals(record.type!!) }
}
