package com.exsample.householdaccounts.controller.widgets

import android.R
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.exsample.householdaccounts.domain.RecordTypeList
import java.util.*

/**
 * Created by ryosuke on 2018/02/10.
 */
fun Spinner.buildRecordTypeAdapter(recordTypeList: RecordTypeList) = adapter(recordTypeList.list.map { it.name!! })

fun Spinner.adapter(list:List<String>){
    val adapter = ArrayAdapter(context, R.layout.simple_spinner_item, list)
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    this.adapter = adapter
}

fun Spinner.buildSearchDateAdapter() {
    val list = mutableListOf<String>()
    val now = Calendar.getInstance()
    val thisYear = now.get(Calendar.YEAR)
    for(i in 2 downTo -2){
        for(y in 1..12){
            list.add("${thisYear - i}-${y}")
        }
    }
    adapter(list)
}
