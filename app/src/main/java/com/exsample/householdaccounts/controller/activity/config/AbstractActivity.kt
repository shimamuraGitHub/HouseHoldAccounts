package com.exsample.householdaccounts.controller.activity.config

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.*
import com.exsample.householdaccounts.R
import com.exsample.householdaccounts.controller.activity.find
import com.exsample.householdaccounts.controller.activity.getInflate
import com.exsample.householdaccounts.controller.activity.navigation.NavigationListener
import com.exsample.householdaccounts.controller.widgets.setRecordTypeAdapter
import com.exsample.householdaccounts.controller.widgets.toEditableSelectedItem
import com.exsample.householdaccounts.db.DBOpenHelper
import com.exsample.householdaccounts.domain.type.RecordTypeList
import kotlinx.android.synthetic.main.app_bar_config.*

/**
 * Created by ryosuke on 2018/03/03.
 */
abstract class AbstractActivity : NavigationListener() {

    private val dbHelper = DBOpenHelper(this, 1)
    val service by lazy { ConfigService(dbHelper) }

    val layout by lazy { getInflate(R.layout.register_record_type) }

    val typeSpinner by lazy { find<Spinner>(R.id.configTypeNames) }
    val nameEdit by lazy { find<EditText>(R.id.configTypeName) }
    val isExpenditure by lazy { find<Switch>(R.id.isExpenditure) }

    val registerNameEdit by lazy { layout.find<EditText>(R.id.registerTypeName) }
    val registerIsExpenditure by lazy { layout.find<Switch>(R.id.registerIsExpenditure) }
    val registerButton by lazy { layout.find<Button>(R.id.execute) }

    /* テーブルデータが更新されるたびに取得し直す */
    lateinit var recordTypes: RecordTypeList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)
        setSupportActionBar(toolbar)

        dbHelper.onCreate(dbHelper.writableDatabase)
        typeSpinner.onItemSelectedListener = onItemSelectedListener
        resetRecordTypes()

        fab.setOnClickListener { _ ->
            AlertDialog.Builder(this).setView(layout).show()
            registerButton.setOnClickListener { register() }
        }
    }

    private val onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(p0: AdapterView<*>?) {}
        override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

            nameEdit.text = typeSpinner.toEditableSelectedItem()
            isExpenditure.isChecked = service.findType(recordTypes,typeSpinner).isExpenditure!!
        }
    }

    protected fun resetRecordTypes() {
        recordTypes = service.findAllEnabled()
        typeSpinner.setRecordTypeAdapter(recordTypes)
    }

    protected abstract fun register()
}