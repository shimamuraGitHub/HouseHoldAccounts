package com.exsample.householdaccounts.controller.widgets.list

import android.view.Gravity
import android.widget.CheckedTextView
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import com.exsample.householdaccounts.controller.activity.history.HistoryActivity
import com.exsample.householdaccounts.controller.message.ConfirmMessage
import com.exsample.householdaccounts.controller.message.ItemsMessage
import com.exsample.householdaccounts.controller.widgets.DialogBuilder
import com.exsample.householdaccounts.domain.record.Record
import com.exsample.householdaccounts.domain.record.RecordList
import com.exsample.householdaccounts.domain.type.RecordTypeList
import com.exsample.householdaccounts.util.toSQLString

/**
 * Created by ryosuke on 2018/02/12.
 */
class HistoryTable(private val tableLayout: TableLayout){

    fun build(recordList: RecordList, typeList: RecordTypeList){

        val indexRow = TableRow(tableLayout.context)

        indexRow.addView(createIndexText("日付"))
        indexRow.addView(createIndexText("項目"))
        indexRow.addView(createIndexText("金額"))
        indexRow.addView(createIndexText("旧"))
        tableLayout.addView(indexRow)

        recordList.forEach {
            it.recordType = typeList.findByRecord(it)
            tableLayout.addView(createRecordRow(it,typeList))
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

    private fun createRecordRow(record: Record,typeList: RecordTypeList):TableRow{

        val recordRow = TableRow(tableLayout.context)
        recordRow.setOnClickListener(record)
        recordRow.addView(createRecordText(record.date!!.toSQLString()))
        recordRow.addView(createRecordText(record.getTypeName()!!,Gravity.LEFT))
        recordRow.addView(createRecordText(record.money.toString(),Gravity.RIGHT))
        recordRow.addView(shouldEditCheck(record,typeList))
        return recordRow
    }

    private fun createRecordText(text:String,gravity:Int? = null):TextView{

        val recordText = TextView(tableLayout.context)

        recordText.text = text
        recordText.textSize = 20.0F
        recordText.setPadding(20,0,20,0)
        if(gravity != null) recordText.gravity = gravity

        return recordText
    }

    private fun shouldEditCheck(record: Record, typeList: RecordTypeList):CheckedTextView{
        val check = CheckedTextView(tableLayout.context)
        check.text = if(record.isLatestType(typeList))"〇" else "×"
        return check
    }

    private fun TableRow.setOnClickListener(record: Record) = setOnClickListener{buildDialog(record).show()}

    private fun buildDialog(record: Record): DialogBuilder {

        val title = "${record.date!!.toSQLString()} ${record.getTypeName()} ${record.money}円"
        val items = arrayOf("編集", "削除")

        val builder = DialogBuilder(tableLayout.context)
        return builder.buildItems(ItemsMessage(title,items), { _, s -> buildConfirmDialog(s,record).show() })
    }

    private fun buildConfirmDialog(selected:Int,record: Record): DialogBuilder {

        val activity = tableLayout.context as HistoryActivity
        val builder = DialogBuilder(activity)
        builder.buildConfirm(ConfirmMessage("よろしいですか？"),{ _, _->
            when (selected) {
                0 -> activity.toMainForEdit(record)
                1 -> activity.service.erase(record)
            }
            removeAllViews()
            activity.buildList(activity.service.findAllRecord())
        })
        return builder
    }

    fun removeAllViews() = tableLayout.removeAllViews()
}
