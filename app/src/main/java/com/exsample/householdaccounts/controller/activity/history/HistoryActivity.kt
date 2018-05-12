package com.exsample.householdaccounts.controller.activity.history

import android.content.Intent
import android.os.Bundle
import com.exsample.householdaccounts.controller.activity.main.MainActivity
import com.exsample.householdaccounts.domain.record.Record
import com.exsample.householdaccounts.domain.record.RecordList
import com.exsample.householdaccounts.domain.type.RecordTypeList
import com.exsample.householdaccounts.util.TARGET

class HistoryActivity : AbstractActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setNavigation()
    }

    override fun search(){

        // 項目スピナーで選択した項目名を持つレコードタイプを生成
        val type = service.findType(enabledRecordTypes,typeSpinner)

        val fromRecord = service.createFromRecord(fromDateSpinner,type,fromMoneyEdit)
        val toRecord = service.createToRecord(toDateSpinner,type, toMoneyEdit)

        historyTable.removeAllViews()
        buildList(service.search(fromRecord,toRecord))
    }

    override fun buildList(records: RecordList){
        historyTable.build(records, recordTypes)
        sumMoney.text = records.sumMoney().toString()
    }

    fun toMainForEdit(target: Record)
            = startActivity(Intent(this, MainActivity::class.java).putExtra(TARGET,target))
}

//    /**
//     * 合計金額、履歴テーブルを再生成する.
//     */
//    fun buildList(){
//        historyTable.removeAllViews()
//
//        val records = service.findAllRecord()
//        historyTable.build(records, recordTypes)
//
//        sumMoney.text = records.sumMoney().toString()
//
//    }

