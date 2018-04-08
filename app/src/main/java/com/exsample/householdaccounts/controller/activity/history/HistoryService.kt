package com.exsample.householdaccounts.controller.activity.history

import android.widget.EditText
import android.widget.Spinner
import com.exsample.householdaccounts.controller.widgets.Year_MonthSpinnerFunctions
import com.exsample.householdaccounts.controller.widgets.setSearchDateAdapter
import com.exsample.householdaccounts.domain.factory.RecordFactory
import com.exsample.householdaccounts.db.DBOpenHelper
import com.exsample.householdaccounts.domain.factory.RecordTypeFactory
import com.exsample.householdaccounts.domain.record.Record
import com.exsample.householdaccounts.domain.record.RecordAgent
import com.exsample.householdaccounts.domain.type.RecordType
import com.exsample.householdaccounts.domain.type.RecordTypeAgent
import com.exsample.householdaccounts.domain.type.RecordTypeList
import com.exsample.householdaccounts.util.getMonth
import java.util.*

/**
 * Created by ryosuke on 2018/02/11.
 */
class HistoryService(helper: DBOpenHelper) : Year_MonthSpinnerFunctions {

    private val recordAgent = RecordAgent(helper)
    private val typeAgent = RecordTypeAgent(helper)

    fun findAllRecord() = recordAgent.search(Record(), Record())

    fun findAllRecordTypes() = typeAgent.findAllTypes()

    fun findType(recordTypes: RecordTypeList, typeSpinner: Spinner)
             = recordTypes.findByName(RecordTypeFactory.create(typeSpinner))

    fun erase(target: Record) = recordAgent.erase(target)

    fun search(from: Record, to: Record) = recordAgent.search(from, to)

    fun createFromRecord(fromDate: Spinner, type: RecordType, fromMoney: EditText)
            = RecordFactory.create(fromDate.createSelectedDateAtFirst(),type, fromMoney)

    fun createToRecord(toDate: Spinner, type: RecordType, toMoney: EditText)
            = RecordFactory.create(toDate.createSelectedDateAtLast(),type, toMoney)

    fun setSpinners(fromDateSpinner:Spinner,toDateSpinner:Spinner){
        val thisMonth = Calendar.getInstance().getMonth()
        setSpinner(fromDateSpinner,24 + thisMonth)
        setSpinner(toDateSpinner,25 + thisMonth)
    }

    private fun setSpinner(dateSpinner: Spinner,selection : Int){
        dateSpinner.setSearchDateAdapter()
        dateSpinner.setSelection(selection)
    }
}