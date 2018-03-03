package com.exsample.householdaccounts.controller.widgets

import android.widget.Spinner
import com.exsample.householdaccounts.util.*
import java.util.*

/**
 * Created by ryosuke on 2018/03/03.
 */
interface DateSpinnerFunctions {

    fun Spinner.selectedDate() = this.selectedItem.toString().toDate()

    fun Spinner.setDateAdapter(){
        val list = mutableListOf<String>()
        val now = Calendar.getInstance()
        val thisYear = now.getYear()
        for(i in -1..1){
            val year = thisYear + i
            for(month in 1..12){
                val lastDay = findLastDay(year,month)
                for(day in 1..lastDay){
                    list.add("${year}-${month.toCode(2)}-${day.toCode(2)}")
                }
            }
        }
        adapter(list)
    }

    fun Spinner.setNowPosition() = this.setPosition(Calendar.getInstance())

    fun Spinner.setDatePosition(date: Date){
        val cal = Calendar.getInstance()
        cal.time = date
        this.setPosition(cal)
    }

    private fun Spinner.setPosition(calendar: Calendar){
        val thisMonth = calendar.getMonth() + 1
        val februaryLast = if (calendar.isLeapYear()) 29 else 28
        var position = 337 + februaryLast
        position += if(thisMonth > 1) 31 else 0
        position += if(thisMonth > 2) februaryLast else 0
        position += if(thisMonth > 3) 31 else 0
        position += if(thisMonth > 4) 30 else 0
        position += if(thisMonth > 5) 31 else 0
        position += if(thisMonth > 6) 30 else 0
        position += if(thisMonth > 7) 31 else 0
        position += if(thisMonth > 8) 31 else 0
        position += if(thisMonth > 9) 30 else 0
        position += if(thisMonth > 10) 30 else 0
        position += if(thisMonth > 11) 30 else 0
        position += calendar.getDayOfMonth()
        position--
        this.setSelection(position)
    }
}