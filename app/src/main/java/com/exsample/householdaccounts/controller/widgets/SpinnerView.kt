package com.exsample.householdaccounts.controller.widgets

import android.R
import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.exsample.householdaccounts.domain.RecordType

/**
 * Created by ryosuke on 2018/02/10.
 */
open class SpinnerView {

    val spinner: Spinner

    constructor(context: Context){
        spinner = Spinner(context)
    }
    constructor(spinner: Spinner){
        this.spinner = spinner
    }

    fun buildRecordTypeAdapter(list: List<RecordType>) = adapter(list.map { it.name!! })

    fun adapter(list:List<String>){
        val adapter = ArrayAdapter(spinner.context, R.layout.simple_spinner_item, list)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }

    fun selectedItem() = spinner.selectedItem

}