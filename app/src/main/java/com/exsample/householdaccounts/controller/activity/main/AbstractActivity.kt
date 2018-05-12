package com.exsample.householdaccounts.controller.activity.main

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import com.exsample.householdaccounts.R
import com.exsample.householdaccounts.controller.activity.find
import com.exsample.householdaccounts.controller.activity.navigation.NavigationListener
import com.exsample.householdaccounts.controller.widgets.DateSpinnerFunctions
import com.exsample.householdaccounts.controller.widgets.setRecordTypeAdapter
import com.exsample.householdaccounts.db.DBOpenHelper
import com.exsample.householdaccounts.domain.record.Record
import com.exsample.householdaccounts.domain.type.RecordTypeList
import com.exsample.householdaccounts.util.TARGET
import com.exsample.householdaccounts.util.toEditable

/**
 * Created by ryosuke on 2018/03/03.
 */
abstract class AbstractActivity : NavigationListener(), DateSpinnerFunctions {

    private val dbHelper = DBOpenHelper(this, 1)
    val service by lazy { MainService(dbHelper) }

    val dateSpinner by lazy { find<Spinner>(R.id.dates) }
    val typeSpinner by lazy { find<Spinner>(R.id.mainTypeNames) }
    val moneyEdit by lazy { find<EditText>(R.id.moneyEdit) }

    val execute by lazy { find<Button>(R.id.execute) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper.onCreate(dbHelper.writableDatabase)

        dateSpinner.setDateAdapter()
        dateSpinner.setNowPosition()

        val recordTypes = service.findRecordTypes()
        typeSpinner.setRecordTypeAdapter(recordTypes)

        val target = intent.getSerializableExtra(TARGET)

        if (target != null) {
            setForEdit(target as Record, recordTypes)
        }
    }

    private fun setForEdit(target: Record, recordTypes: RecordTypeList) {
        moneyEdit.text.append(target.money.toString())
        typeSpinner.setSelection(recordTypes.indexOfByCode(target))
        dateSpinner.setDatePosition(target.date!!)
        execute.text = "変更".toEditable()
        execute.setOnClickListener {_-> update(target) }
    }

    abstract fun update(target: Record)
}