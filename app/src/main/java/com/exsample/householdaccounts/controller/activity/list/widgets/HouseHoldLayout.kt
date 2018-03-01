package com.exsample.householdaccounts.controller.widgets.list

import android.view.Gravity
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import com.exsample.householdaccounts.controller.activity.list.ListActivity
import com.exsample.householdaccounts.controller.message.ConfirmMessage
import com.exsample.householdaccounts.controller.message.ItemsMessage
import com.exsample.householdaccounts.controller.widgets.DialogBuilder
import com.exsample.householdaccounts.domain.Record
import com.exsample.householdaccounts.domain.RecordList
import com.exsample.householdaccounts.domain.RecordTypeList

/**
 * Created by ryosuke on 2018/02/12.
 */
class HouseHoldLayout(val tableLayout: TableLayout){

    fun build(recordList:RecordList,typeList:RecordTypeList){

        val indexRow = TableRow(tableLayout.context)

        indexRow.addView(createIndexText("日付"))
        indexRow.addView(createIndexText("項目"))
        indexRow.addView(createIndexText("金額"))
        tableLayout.addView(indexRow)

        recordList.forEach {

            it.recordType = typeList.findByCode(it)

            val recordRow = TableRow(tableLayout.context)

            recordRow.setOnClickListener(it)
            recordRow.addView(createRecordText(it.getAdjustedDate()))
            recordRow.addView(createRecordText(it.getTypeName()!!,Gravity.LEFT))
            recordRow.addView(createRecordText(it.toStringMoney(),Gravity.RIGHT))
            tableLayout.addView(recordRow)
        }
    }

    private fun createIndexText(text:String):TextView{

        val indexText = TextView(tableLayout.context)

        indexText.text = text
        indexText.gravity = Gravity.CENTER
        indexText.textSize = 30.0F
        indexText.setPadding(20,0,20,0)

        return indexText
    }

    private fun createRecordText(text:String,gravity:Int? = null):TextView{

        val recordText = TextView(tableLayout.context)

        recordText.text = text
        recordText.textSize = 20.0F
        recordText.setPadding(20,0,20,0)
        if(gravity != null) recordText.gravity = gravity

        return recordText
    }

    private fun TableRow.setOnClickListener(record: Record) = setOnClickListener{buildDialog(record).show()}

    private fun buildDialog(record: Record): DialogBuilder {


        val title = "${record.getAdjustedDate()} ${record.getTypeName()} ${record.money}円"
        val items = arrayOf("編集", "削除")

        val builder = DialogBuilder(tableLayout.context)
        return builder.buildItems(ItemsMessage(title,items), { _, s -> buildConfirmDialog(s,record).show() })
    }

    private fun buildConfirmDialog(selected:Int,record: Record): DialogBuilder {

        val context = tableLayout.context
        val builder = DialogBuilder(context)
        builder.buildConfirm(ConfirmMessage("よろしいですか？"),{ _, _->
            val activity = context as ListActivity
            when (selected) {
                1 -> activity.service.erase(record)
            }
            activity.reBuildList()
        })
        return builder
    }

    fun removeAllViews() = tableLayout.removeAllViews()
}
