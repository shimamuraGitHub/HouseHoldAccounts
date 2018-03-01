package com.exsample.householdaccounts.controller.activity.list

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.exsample.householdaccounts.R
import com.exsample.householdaccounts.controller.activity.find
import com.exsample.householdaccounts.controller.activity.navigation.NavigationListener
import com.exsample.householdaccounts.controller.widgets.setRecordTypeAdapter
import com.exsample.householdaccounts.controller.widgets.setSearchDateAdapter
import com.exsample.householdaccounts.controller.widgets.list.HouseHoldLayout
import com.exsample.householdaccounts.db.DBOpenHelper
import com.exsample.householdaccounts.domain.RecordTypeList

import kotlinx.android.synthetic.main.activity_list.*
import java.util.*

class ListActivity : NavigationListener() {

    val dbHelper = DBOpenHelper(context = this,version = 1)

    lateinit var service : ListService

    lateinit var houseHoldLayout:HouseHoldLayout
    lateinit var typeSpinner :Spinner
    lateinit var fromDateSpinner :Spinner
    lateinit var toDateSpinner :Spinner
    lateinit var fromMoneyEdit :EditText
    lateinit var toMoneyEdit :EditText

    lateinit var recordTypeList:RecordTypeList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
//        setSupportActionBar(toolbar)

        service = ListService(dbHelper)

        recordTypeList = service.findAllRecordTypes()

        houseHoldLayout = HouseHoldLayout(find<TableLayout>(R.id.accountTable))
        houseHoldLayout.build(service.findAllRecord(),recordTypeList)

        fab.setOnClickListener {

            val layout = getInflate()
            AlertDialog.Builder(this).setView(layout).show()
            findLayoutViews(layout)

            val thisMonth = Calendar.getInstance().get(Calendar.MONTH)
            setSpinner(24 + thisMonth)
            setSpinner(25 + thisMonth)

            val typeList = service.findAllRecordTypes()
            typeSpinner.setRecordTypeAdapter(typeList)

            val search = layout.find<Button>(R.id.search)
            search.setOnClickListener {search(typeList)}
        }
        setNavigation()
    }

    private fun getInflate() = LayoutInflater.from(this).inflate(R.layout.search_record_list,null)

    private fun findLayoutViews(layout: View){
        typeSpinner = layout.find<Spinner>(R.id.spinner)
        fromDateSpinner = layout.find<Spinner>(R.id.dateSpinner1)
        toDateSpinner = layout.find<Spinner>(R.id.dateSpinner2)
        fromMoneyEdit = layout.find<EditText>(R.id.moneyEdit1)
        toMoneyEdit = layout.find<EditText>(R.id.moneyEdit2)
    }

    private fun setSpinner(selection : Int){
        toDateSpinner.setSearchDateAdapter()
        toDateSpinner.setSelection(selection)
    }

    private fun search(typeList:RecordTypeList){

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
}