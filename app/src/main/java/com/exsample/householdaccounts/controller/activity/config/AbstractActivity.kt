package com.exsample.householdaccounts.controller.activity.config

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.exsample.householdaccounts.R
import com.exsample.householdaccounts.controller.activity.find
import com.exsample.householdaccounts.controller.activity.getInflate
import com.exsample.householdaccounts.controller.activity.navigation.NavigationListener
import com.exsample.householdaccounts.controller.widgets.setRecordTypeAdapter
import com.exsample.householdaccounts.controller.widgets.toEditableSelectedItem
import com.exsample.householdaccounts.db.DBOpenHelper
import com.exsample.householdaccounts.domain.RecordTypeList
import kotlinx.android.synthetic.main.app_bar_config.*

/**
 * Created by ryosuke on 2018/03/03.
 */
abstract class AbstractActivity : NavigationListener() {

    val dbHelper = DBOpenHelper(context = this, version = 1)
    val service by lazy { ConfigService(dbHelper) }

    val layout by lazy { getInflate(R.layout.register_record_type) }

    val typeSpinner by lazy { find<Spinner>(R.id.spinner) }
    val nameEdit by lazy { find<EditText>(R.id.editText) }
    val isExpenditure by lazy { find<Switch>(R.id.switch1) }

    val registerNameEdit by lazy { layout.find<EditText>(R.id.editText) }
    val registerIsExpenditure by lazy { layout.find<Switch>(R.id.switch1) }
    val registerButton by lazy { layout.find<Button>(R.id.execute) }

    /* テーブルデータが更新されるたびに取得し直す */
    lateinit var recordTypes: RecordTypeList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)
        setSupportActionBar(toolbar)

        dbHelper.onCreate(dbHelper.writableDatabase)
        setListener()
        resetRecordTypes()

        fab.setOnClickListener { view ->
            AlertDialog.Builder(this).setView(layout).show()
            registerButton.setOnClickListener { register() }
        }
    }

    protected fun resetRecordTypes() {
        recordTypes = service.findAllEnabled()
        typeSpinner.setRecordTypeAdapter(recordTypes)
    }

    private fun setListener() {
        typeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                nameEdit.text = typeSpinner.toEditableSelectedItem()
                isExpenditure.isChecked = recordTypes.findBySelectedName(typeSpinner).isExpenditure!!
            }
        }
    }

    protected abstract fun register()
}