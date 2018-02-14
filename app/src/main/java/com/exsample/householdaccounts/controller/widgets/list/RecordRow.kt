package com.exsample.householdaccounts.controller.widgets.view.list

import android.support.v7.app.AlertDialog
import android.widget.TableRow
import com.exsample.householdaccounts.controller.activity.list.ListActivity
import com.exsample.householdaccounts.controller.widgets.TableRowView
import com.exsample.householdaccounts.domain.Record

/**
 * Created by ryosuke on 2018/02/11.
 */
class RecordRow(tableRow: TableRow,val record: Record): TableRowView(tableRow) {

    init{
//        tableRow.tag = record.id
        tableRow.setOnClickListener { buildDialog().show() }
    }

    private fun buildDialog(): AlertDialog.Builder {
        val message = "${record.getAdjustedDate()} ${record.getTypeName()} ${record.money}円"
        val dialog = AlertDialog.Builder(tableRow.context)
                .setTitle(message)
                .setItems(arrayOf("編集", "削除"), { d, selected -> buildConfirmDialog(selected).show() })
        return dialog
    }

    private fun buildConfirmDialog(selected:Int) = AlertDialog.Builder(tableRow.context)
            .setMessage("よろしいですか？")
            .setPositiveButton("OK",{d,i->
                val activity = tableRow.context as ListActivity
                when(selected){
                    1->activity.service.erase(record)
                }
                activity.reBuildList()
            })
            .setNegativeButton("キャンセル",null)

}
