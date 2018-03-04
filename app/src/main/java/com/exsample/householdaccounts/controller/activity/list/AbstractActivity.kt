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
import com.exsample.householdaccounts.controller.activity.getInflate
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
abstract class AbstractActivity : NavigationListener() {

    val dbHelper = DBOpenHelper(context = this, version = 1)
    val service by lazy { ListService(dbHelper) }

    val layout by lazy { getInflate(R.layout.search_record_list) }

    val houseHoldLayout by lazy { HouseHoldLayout(find<TableLayout>(R.id.accountTable)) }
    val typeSpinner by lazy { layout.find<Spinner>(R.id.spinner) }
    val fromDateSpinner by lazy { layout.find<Spinner>(R.id.dateSpinner1) }
    val toDateSpinner by lazy { layout.find<Spinner>(R.id.dateSpinner2) }
    val fromMoneyEdit by lazy { layout.find<EditText>(R.id.moneyEdit1) }
    val toMoneyEdit by lazy { layout.find<EditText>(R.id.moneyEdit2) }
    val searchButton by lazy { layout.find<Button>(R.id.search) }

    val recordTypeList by lazy { service.findAllRecordTypes() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        houseHoldLayout.build(service.findAllRecord(), recordTypeList)

        fab.setOnClickListener {
            AlertDialog.Builder(this).setView(layout).show()
            service.setSpinners(fromDateSpinner, toDateSpinner)
            typeSpinner.setRecordTypeAdapter(recordTypeList)
            searchButton.setOnClickListener { search(recordTypeList) }
        }
    }

    protected abstract fun search(typeList: RecordTypeList)
}