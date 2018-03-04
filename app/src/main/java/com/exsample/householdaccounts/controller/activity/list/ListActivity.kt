package com.exsample.householdaccounts.controller.activity.list

import android.content.Intent
import android.os.Bundle
import com.exsample.householdaccounts.controller.activity.main.MainActivity
import com.exsample.householdaccounts.domain.Record
import com.exsample.householdaccounts.domain.RecordTypeList
import com.exsample.householdaccounts.util.TARGET

class ListActivity : AbstractActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setNavigation()
    }

    override fun search(typeList:RecordTypeList){

        val type = typeList.findBySelectedName(typeSpinner)

        val fromRecord = service.createFromRecord(fromDateSpinner,type,fromMoneyEdit)
        val toRecord = service.createToRecord(toDateSpinner,type, toMoneyEdit)

        val result = service.search(fromRecord,toRecord)
        houseHoldLayout.removeAllViews()
        houseHoldLayout.build(result,recordTypeList)
    }

    fun reBuildList(){
        houseHoldLayout.removeAllViews()
        houseHoldLayout.build(service.findAllRecord(),recordTypeList)
    }

    fun toMainForEdit(target: Record)
            = startActivity(Intent(this, MainActivity::class.java).putExtra(TARGET,target))
}