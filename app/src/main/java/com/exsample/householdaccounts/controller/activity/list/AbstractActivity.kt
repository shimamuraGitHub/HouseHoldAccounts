package com.exsample.householdaccounts.controller.activity.list

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TableLayout
import com.exsample.householdaccounts.R
import com.exsample.householdaccounts.controller.activity.find
import com.exsample.householdaccounts.controller.activity.navigation.NavigationListener
import com.exsample.householdaccounts.controller.widgets.list.HouseHoldLayout
import com.exsample.householdaccounts.controller.widgets.setRecordTypeAdapter
import com.exsample.householdaccounts.controller.widgets.setSearchDateAdapter
import com.exsample.householdaccounts.db.DBOpenHelper
import com.exsample.householdaccounts.domain.RecordTypeList
import kotlinx.android.synthetic.main.activity_list.*
import java.util.*

/**
 * Created by ryosuke on 2018/03/03.
 */
abstract class AbstractActivity: NavigationListener()  {

    val dbHelper = DBOpenHelper(context = this,version = 1)

    lateinit var service : ListService

    val layout by lazy{getInflate()}

    lateinit var houseHoldLayout: HouseHoldLayout
    val typeSpinner by lazy{layout.find<Spinner>(R.id.spinner)}
    lateinit var fromDateSpinner : Spinner
    lateinit var toDateSpinner : Spinner
    lateinit var fromMoneyEdit : EditText
    lateinit var toMoneyEdit : EditText

    lateinit var recordTypeList:RecordTypeList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        service = ListService(dbHelper)

        recordTypeList = service.findAllRecordTypes()

        houseHoldLayout = HouseHoldLayout(find<TableLayout>(R.id.accountTable))
        val test = service.test()
        houseHoldLayout.build(service.findAllRecord(),recordTypeList)

        fab.setOnClickListener {

//            val layout = getInflate()
            AlertDialog.Builder(this).setView(layout).show()
            findLayoutViews(layout)

            service.setSpinners(fromDateSpinner,toDateSpinner)

            typeSpinner.setRecordTypeAdapter(recordTypeList)

            val search = layout.find<Button>(R.id.search)
            search.setOnClickListener {search(recordTypeList)}
        }
    }

    private fun getInflate() = LayoutInflater.from(this).inflate(R.layout.search_record_list,null)

    private fun findLayoutViews(layout: View){
//        typeSpinner = layout.find<Spinner>(R.id.spinner)
        fromDateSpinner = layout.find<Spinner>(R.id.dateSpinner1)
        toDateSpinner = layout.find<Spinner>(R.id.dateSpinner2)
        fromMoneyEdit = layout.find<EditText>(R.id.moneyEdit1)
        toMoneyEdit = layout.find<EditText>(R.id.moneyEdit2)
    }

    protected abstract fun search(typeList: RecordTypeList)
}