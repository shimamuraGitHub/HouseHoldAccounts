package com.exsample.householdaccounts.controller.activity.history

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TableLayout
import com.exsample.householdaccounts.R
import com.exsample.householdaccounts.controller.activity.find
import com.exsample.householdaccounts.controller.activity.getInflate
import com.exsample.householdaccounts.controller.activity.navigation.NavigationListener
import com.exsample.householdaccounts.controller.activity.removeFromParent
import com.exsample.householdaccounts.controller.widgets.list.HistoryTable
import com.exsample.householdaccounts.controller.widgets.setRecordTypeAdapter
import com.exsample.householdaccounts.db.DBOpenHelper
import com.exsample.householdaccounts.domain.type.RecordTypeList
import kotlinx.android.synthetic.main.activity_history.*

/**
 * Created by ryosuke on 2018/03/03.
 */
abstract class AbstractActivity : NavigationListener() {

    val dbHelper = DBOpenHelper(this, 1)
    val service by lazy { HistoryService(dbHelper) }

    val layout by lazy { getInflate(R.layout.search_history) }

    val historyTable by lazy { HistoryTable(find<TableLayout>(R.id.historyTable)) }
    val typeSpinner by lazy { layout.find<Spinner>(R.id.historyTypeNames) }
    val fromDateSpinner by lazy { layout.find<Spinner>(R.id.fromDates) }
    val toDateSpinner by lazy { layout.find<Spinner>(R.id.toDates) }
    val fromMoneyEdit by lazy { layout.find<EditText>(R.id.fromMoney) }
    val toMoneyEdit by lazy { layout.find<EditText>(R.id.toMoney) }
    val search by lazy { layout.find<Button>(R.id.search) }

    val recordTypes by lazy { service.findAllRecordTypes() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

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