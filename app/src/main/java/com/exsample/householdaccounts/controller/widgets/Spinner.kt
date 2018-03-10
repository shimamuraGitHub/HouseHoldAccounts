package com.exsample.householdaccounts.controller.widgets

import android.R
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.exsample.householdaccounts.domain.type.RecordTypeList
import com.exsample.householdaccounts.util.*
import java.util.*

/**
 * Created by ryosuke on 2018/02/10.
 */
fun Spinner.setRecordTypeAdapter(typeList: RecordTypeList) = adapter(typeList.map { it.name!! })

fun Spinner.adapter(list:List<String>){
    val adapter = ArrayAdapter(context, R.layout.simple_spinner_item, list)
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    this.adapter = adapter
}

fun Spinner.setSearchDateAdapter() {
    val list = mutableListOf<String>()
    val thisYear = Calendar.getInstance().getYear()
    for(i in -2..2){
        for(y in 1..12){
            list.add("${thisYear + i}-${y.toCode(2)}")
        }
    }
    adapter(list)
}

fun Spinner.toStringSelectedItem() = this.selectedItem.toString()

fun Spinner.toEditableSelectedItem() = this.toStringSelectedItem().toEditable()
