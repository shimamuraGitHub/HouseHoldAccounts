package com.exsample.householdaccounts.domain

import android.widget.Spinner

/**
 * Created by ryosuke on 2018/02/11.
 */
class RecordTypeList(val list:List<RecordType>) {
    fun findByCode(record: Record) = list.filter { it.code.equals(record.type) }.first()

    fun findByNameSpinner(typeNameSpinner: Spinner) = list.filter { it.name!!.equals(typeNameSpinner.selectedItem) }.first()
}