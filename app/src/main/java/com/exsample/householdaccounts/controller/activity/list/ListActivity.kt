package com.exsample.householdaccounts.controller.activity.list

import android.content.Intent
import android.os.Bundle
import com.exsample.householdaccounts.controller.activity.main.MainActivity
import com.exsample.householdaccounts.domain.record.Record
import com.exsample.householdaccounts.domain.type.RecordTypeList
import com.exsample.householdaccounts.util.TARGET

class ListActivity : AbstractActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setNavigation()
    }

    override fun search(typeList: RecordTypeList){

        val type = typeList.findByName(service.findType(recordTypes,typeSpinner))

        val fromRecord = service.createFromRecord(fromDateSpinner,type,fromMoneyEdit)
        val toRecord = service.createToRecord(toDateSpinner,type, toMoneyEdit)
        val result = service.search(fromRecord,toRecord)

        historyTable.removeAllViews()
        historyTable.build(result, recordTypes)
    }

    fun reBuildList(){
        historyTable.removeAllViews()
        historyTable.build(service.findAllRecord(), recordTypes)
    }

    fun toMainForEdit(target: Record)
            = startActivity(Intent(this, MainActivity::class.java).putExtra(TARGET,target))
}