package com.exsample.householdaccounts.domain

import com.exsample.householdaccounts.controller.widgets.SpinnerView

/**
 * Created by ryosuke on 2018/02/11.
 */
class RecordTypeList(val list:List<RecordType>) {
    fun findByCode(record: Record) = list.filter { it.code.equals(record.type) }.first()

    fun findByNameSpinner(typeNameSpinner: SpinnerView) = list.filter { it.name!!.equals(typeNameSpinner.selectedItem()) }.first()
}