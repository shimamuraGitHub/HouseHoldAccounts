package com.exsample.householdaccounts.controller.widgets

import android.content.Context
import android.view.View
import android.widget.TableRow

/**
 * Created by ryosuke on 2018/02/10.
 */
open class TableRowView {

    val tableRow: TableRow

    constructor(context: Context){
        tableRow = TableRow(context)
    }

    constructor(tableRow: TableRow){
        this.tableRow = tableRow
    }

    fun addView(v: View) = tableRow.addView(v)
}