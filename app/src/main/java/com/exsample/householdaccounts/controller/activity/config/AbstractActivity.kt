package com.exsample.householdaccounts.controller.activity.config

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.exsample.householdaccounts.R
import com.exsample.householdaccounts.controller.activity.find
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
    val dbHelper = DBOpenHelper(context = this,version = 1)
    lateinit var service : ConfigService

    lateinit var typeSpinner: Spinner
    lateinit var nameEdit: EditText
    lateinit var isExpenditure: Switch

    lateinit var registerNameEdit: EditText
    lateinit var registerIsExpenditure: Switch
    lateinit var registerButton: Button

    /* テーブルデータが更新されるたびに取得し直す */
    lateinit var recordTypes : RecordTypeList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)
        setSupportActionBar(toolbar)

        dbHelper.onCreate(dbHelper.writableDatabase)
        service = ConfigService(dbHelper)

        findViews()

        resetRecordTypes()

        fab.setOnClickListener { view ->
            val layout = getInflate()
            AlertDialog.Builder(this).setView(layout).show()
            findLayoutViews(layout)

            registerButton.setOnClickListener { register() }
        }
    }

    private fun getInflate() = LayoutInflater.from(this).inflate(R.layout.register_record_type,null)

    private fun findLayoutViews(layout: View){
        registerNameEdit = layout.find<EditText>(R.id.editText)
        registerIsExpenditure = layout.find<Switch>(R.id.switch1)
        registerButton = layout.find<Button>(R.id.execute)
    }

    private fun findViews(){
        typeSpinner = find<Spinner>(R.id.spinner)
        nameEdit = find<EditText>(R.id.editText)
        isExpenditure = find<Switch>(R.id.switch1)
        setListener()
    }

    protected fun resetRecordTypes(){
        recordTypes = service.findAllEnabled()
        typeSpinner.setRecordTypeAdapter(recordTypes)
    }

    private fun setListener(){
        typeSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                nameEdit.text = typeSpinner.toEditableSelectedItem()
                isExpenditure.isChecked = recordTypes.findBySelectedName(typeSpinner).isExpenditure!!
            }
        }
    }

    protected abstract fun register()
}