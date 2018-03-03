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
import com.exsample.householdaccounts.domain.Record
import com.exsample.householdaccounts.domain.RecordTypeList
import com.exsample.householdaccounts.util.TARGET
import com.exsample.householdaccounts.util.toEditable

/**
 * Created by ryosuke on 2018/03/03.
 */
abstract class AbstractActivity :NavigationListener(), DateSpinnerFunctions {

    val dbHelper = DBOpenHelper(context = this,version = 1)
    lateinit var service: MainService

    lateinit var dateSpinner: Spinner
    lateinit var typeSpinner: Spinner
    lateinit var moneyEdit: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper.onCreate(dbHelper.writableDatabase)
        service = MainService(dbHelper)

        findViews()

        dateSpinner.setDateAdapter()
        dateSpinner.setNowPosition()

        val recordTypes = service.findRecordTypes()
        typeSpinner.setRecordTypeAdapter(recordTypes)

        val target = intent.getSerializableExtra(TARGET)

        if(target != null){
            target as Record

            setForEdit(target,recordTypes)

            val button = find<Button>(R.id.execute)
            button.text = "変更".toEditable()
            button.setOnClickListener { v-> update(target) }
        }
    }

    private fun findViews(){
        dateSpinner = find<Spinner>(R.id.dates)
        typeSpinner = find<Spinner>(R.id.typeNames)
        moneyEdit = find<EditText>(R.id.moneyEdit)
    }

    private fun setForEdit(target:Record,recordTypes:RecordTypeList){
        moneyEdit.text.append(target.toStringMoney())
        typeSpinner.setSelection(recordTypes.indexOfByCode(target))
        dateSpinner.setDatePosition(target.date!!)
    }

    abstract fun update(target: Record)
}