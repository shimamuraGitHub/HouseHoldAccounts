package com.exsample.householdaccounts.controller.widgets.list

import android.content.Context
import android.view.Gravity
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import com.exsample.householdaccounts.controller.widgets.view.list.IndexRow
import com.exsample.householdaccounts.controller.widgets.view.list.IndexText
import com.exsample.householdaccounts.controller.widgets.view.list.RecordRow
import com.exsample.householdaccounts.controller.widgets.view.list.RecordText
import com.exsample.householdaccounts.domain.Record
import com.exsample.householdaccounts.domain.RecordTypeList

/**
 * Created by ryosuke on 2018/02/12.
 */
class HouseHoldLayout(val tableLayout: TableLayout){
    fun build(context:Context,recordList:List<Record>,typeList:RecordTypeList){
        val indexRow = IndexRow(TableRow(context))
        indexRow.addView(IndexText(TextView(context),"日付").textView)
        indexRow.addView(IndexText(TextView(context),"項目").textView)
        indexRow.addView(IndexText(TextView(context),"金額").textView)
        tableLayout.addView(indexRow.tableRow)

        fun createRecordDateText(record: Record)
                = RecordText(TextView(context),record.getAdjustedDate())
        fun createRecordTypeText(record: Record)
                = RecordText(TextView(context),record.getTypeName()!!, Gravity.LEFT)
        fun createRecordMoneyText(record: Record)
                = RecordText(TextView(context),record.toStringMoney(), Gravity.RIGHT)

        recordList.forEach {
            it.recordType = typeList.findByCode(it)
            val recordRow = RecordRow(TableRow(context),it)
            recordRow.addView(createRecordDateText(it).textView)
            recordRow.addView(createRecordTypeText(it).textView)
            recordRow.addView(createRecordMoneyText(it).textView)
            tableLayout.addView(recordRow.tableRow)
        }
    }

    fun removeAllViews() = tableLayout.removeAllViews()
}