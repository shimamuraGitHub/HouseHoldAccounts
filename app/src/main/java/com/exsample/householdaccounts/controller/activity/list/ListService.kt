package com.exsample.householdaccounts.controller.activity.list

import android.widget.EditText
import android.widget.Spinner
import com.exsample.householdaccounts.controller.widgets.Year_MonthSpinnerFunctions
import com.exsample.householdaccounts.controller.widgets.setSearchDateAdapter
import com.exsample.householdaccounts.factory.RecordFactory
import com.exsample.householdaccounts.db.DBOpenHelper
import com.exsample.householdaccounts.domain.Record
import com.exsample.householdaccounts.domain.RecordAgent
import com.exsample.householdaccounts.domain.RecordList
import com.exsample.householdaccounts.domain.RecordType
import java.util.*

/**
 * Created by ryosuke on 2018/02/11.
 */
class ListService(helper: DBOpenHelper) : Year_MonthSpinnerFunctions {

    val recordAgent = RecordAgent(helper)
    val recordFactory = RecordFactory()

    fun findAllRecord(): RecordList {
        return recordAgent.search(Record(),Record())
    }

    fun test() = recordAgent.findAll()

    fun findAllRecordTypes() = recordAgent.findAllTypes()

    fun erase(target: Record) = recordAgent.erase(target)

    fun search(from: Record, to: Record) = recordAgent.search(from, to)

    fun createFromRecord(fromDate: Spinner, type: RecordType, fromMoney: EditText)
            = recordFactory.create(fromDate.createSelectedDateAtFirst(),type, fromMoney)

    fun createToRecord(toDate: Spinner, type: RecordType, toMoney: EditText)
            = recordFactory.create(toDate.createSelectedDateAtLast(),type, toMoney)

    fun setSpinners(fromDateSpinner:Spinner,toDateSpinner:Spinner){
        val thisMonth = Calendar.getInstance().get(Calendar.MONTH)
        setSpinner(fromDateSpinner,24 + thisMonth)
        setSpinner(toDateSpinner,25 + thisMonth)
    }

    private fun setSpinner(dateSpinner: Spinner,selection : Int){
        dateSpinner.setSearchDateAdapter()
        dateSpinner.setSelection(selection)
    }
}