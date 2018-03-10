package com.exsample.householdaccounts.controller.activity.list

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TableLayout
import com.exsample.householdaccounts.R
import com.exsample.householdaccounts.controller.activity.find
import com.exsample.householdaccounts.controller.activity.getInflate
import com.exsample.householdaccounts.controller.activity.navigation.NavigationListener
import com.exsample.householdaccounts.controller.activity.removeFromParent
import com.exsample.householdaccounts.controller.widgets.list.HouseHoldLayout
import com.exsample.householdaccounts.controller.widgets.setRecordTypeAdapter
import com.exsample.householdaccounts.db.DBOpenHelper
import com.exsample.householdaccounts.domain.type.RecordTypeList
import kotlinx.android.synthetic.main.activity_list.*

/**
 * Created by ryosuke on 2018/03/03.
 */
abstract class AbstractActivity : NavigationListener() {

    val dbHelper = DBOpenHelper(this, 1)
    val service by lazy { ListService(dbHelper) }

    val layout by lazy { getInflate(R.layout.search_record_list) }

    val historyTable by lazy { HouseHoldLayout(find<TableLayout>(R.id.historyTable)) }
    val typeSpinner by lazy { layout.find<Spinner>(R.id.historyTypeNames) }
    val fromDateSpinner by lazy { layout.find<Spinner>(R.id.fromDates) }
    val toDateSpinner by lazy { layout.find<Spinner>(R.id.toDates) }
    val fromMoneyEdit by lazy { layout.find<EditText>(R.id.fromMoney) }
    val toMoneyEdit by lazy { layout.find<EditText>(R.id.toMoney) }
    val search by lazy { layout.find<Button>(R.id.search) }

    val recordTypes by lazy { service.findAllRecordTypes() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        historyTable.build(service.findAllRecord(), recordTypes)

        fab.setOnClickListener {
            layout.removeFromParent()
            AlertDialog.Builder(this).setView(layout).show()
            service.setSpinners(fromDateSpinner, toDateSpinner)
            typeSpinner.setRecordTypeAdapter(recordTypes)
            search.setOnClickListener { search(recordTypes) }
        }
    }

    protected abstract fun search(typeList: RecordTypeList)
}