package com.exsample.householdaccounts.controller.activity.list

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TableLayout
import com.exsample.householdaccounts.R
import com.exsample.householdaccounts.controller.factory.RecordFactory
import com.exsample.householdaccounts.controller.widgets.EditTextView
import com.exsample.householdaccounts.controller.widgets.SpinnerView
import com.exsample.householdaccounts.controller.widgets.list.HouseHoldLayout
import com.exsample.householdaccounts.db.DBOpenHelper
import com.exsample.householdaccounts.domain.RecordTypeList
import com.exsample.householdaccounts.util.createFirstDate
import com.exsample.householdaccounts.util.createLastDate

import kotlinx.android.synthetic.main.activity_list.*
import java.util.*

class ListActivity : AppCompatActivity() {

    val dbHelper = DBOpenHelper(context = this,version = 1)

    val recordFactory = RecordFactory()

    lateinit var service : ListService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {

            AlertDialog.Builder(this).setView(R.layout.search_record_list).show()

            val typeSpinner = SpinnerView(findViewById<Spinner>(R.id.typeSpinner))

            val recordTypes = RecordTypeList(service.findAllRecordTypes())
            typeSpinner.buildRecordTypeAdapter(recordTypes.list)

            val search = findViewById<Button>(R.id.search)
            search.setOnClickListener {

                val type = recordTypes.findByNameSpinner(typeSpinner)

                fun create(id1: Int, body: (String) -> Date, id2: Int) = recordFactory.create(
                        SpinnerView(findViewById<Spinner>(id1)),
                        body,
                        type,
                        EditTextView(findViewById<EditText>(id2))
                )

                val recordA = create(R.id.dateSpinner1, ::createFirstDate, R.id.moneyEdit1)
                val recordB = create(R.id.dateSpinner2, ::createLastDate, R.id.moneyEdit2)

            }
        }

        service = ListService(dbHelper)

        buildList(HouseHoldLayout(findViewById<TableLayout>(R.id.accountTable)))
    }

    private fun buildList(tableLayout: HouseHoldLayout)
            = tableLayout.build(this,service.findAllRecord(),RecordTypeList(service.findAllRecordTypes()))

    fun reBuildList(){
        val tableLayout = HouseHoldLayout(findViewById<TableLayout>(R.id.accountTable))
        tableLayout.removeAllViews()
        buildList(tableLayout)
    }
}
