package com.exsample.householdaccounts.controller.widgets

import android.widget.Spinner
import com.exsample.householdaccounts.util.findLastDay
import com.exsample.householdaccounts.util.toDate
import java.util.*

/**
 * Created by ryosuke on 2018/03/03.
 */
interface Year_MonthSpinnerFunctions {

    fun Spinner.createSelectedDateAtFirst() = "${this.selectedItem.toString()}-01".toDate()

    fun Spinner.createSelectedDateAtLast() : Date {
        val selected = this.selectedItem.toString()
        val dates = selected.split("-").map{it.toInt()}
        return "${selected}-${findLastDay(dates[0],dates[1])}".toDate()
    }

}