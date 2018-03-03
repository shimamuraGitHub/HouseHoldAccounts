package com.exsample.householdaccounts.domain

import android.widget.EditText
import android.widget.Spinner
import com.exsample.householdaccounts.controller.widgets.text
import com.exsample.householdaccounts.util.toCode

/**
 * Created by ryosuke on 2018/02/11.
 */
class RecordTypeList(val list:List<RecordType>) {

    fun<T> map(func:(RecordType) -> T) = list.map(func)

    fun first() = list.first()

    fun findByCode(record: Record) = list.filter { it.code.equals(record.type) }.first()

    fun findByNameSpinner(typeNames: Spinner) = list.filter { it.name!!.equals(typeNames.selectedItem) }.first()

    fun existsByName(edit: EditText) = list.any { it.name!!.equals(edit.text()) }

    fun hasNameWithDifferCode(type: RecordType) = list.any { it.equalsNameWithDifferCode(type) }

    fun createEnableMinCode() : String {
        fun List<Int>.test(test:Int) = !this.any{it.equals(test + 1)}
        val hoge = list.map { it.code!!.toInt() }
        return (hoge.sorted().first { hoge.test(it) } + 1).toCode(3)
    }

    fun indexOfByCode(record: Record) = list.indexOfFirst { it.code.equals(record.type!!) }
}
