package com.exsample.householdaccounts.controller.activity.list

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.exsample.householdaccounts.R
import com.exsample.householdaccounts.controller.activity.find
import com.exsample.householdaccounts.controller.activity.main.MainActivity
import com.exsample.householdaccounts.controller.activity.navigation.NavigationListener
import com.exsample.householdaccounts.controller.widgets.setRecordTypeAdapter
import com.exsample.householdaccounts.controller.widgets.setSearchDateAdapter
import com.exsample.householdaccounts.controller.widgets.list.HouseHoldLayout
import com.exsample.householdaccounts.db.DBOpenHelper
import com.exsample.householdaccounts.domain.Record
import com.exsample.householdaccounts.domain.RecordTypeList
import com.exsample.householdaccounts.util.TARGET

import kotlinx.android.synthetic.main.activity_list.*
import java.util.*

class ListActivity : AbstractActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setNavigation()
    }

    override fun search(typeList:RecordTypeList){

        val type = typeList.findByNameSpinner(typeSpinner)

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