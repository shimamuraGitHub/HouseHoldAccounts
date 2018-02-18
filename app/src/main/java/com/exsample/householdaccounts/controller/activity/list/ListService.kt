package com.exsample.householdaccounts.controller.activity.list

import android.widget.EditText
import android.widget.Spinner
import com.exsample.householdaccounts.controller.factory.RecordFactory
import com.exsample.householdaccounts.db.DBOpenHelper
import com.exsample.householdaccounts.domain.Record
import com.exsample.householdaccounts.domain.RecordAgent
import com.exsample.householdaccounts.domain.RecordType
import com.exsample.householdaccounts.util.createFirstDate
import com.exsample.householdaccounts.util.createLastDate

/**
 * Created by ryosuke on 2018/02/11.
 */
class ListService(helper: DBOpenHelper) {

    val recordAgent = RecordAgent(helper)
    val recordFactory = RecordFactory()

    fun findAllRecord() = recordAgent.findAll()

    fun findAllRecordTypes() = recordAgent.findAllTypes()

    fun erase(record: Record) = recordAgent.erase(record)

    fun search(fromRecord: Record, toRecord: Record) = recordAgent.search(fromRecord, toRecord)

    fun createFromRecord(fromDateSpinner: Spinner,type: RecordType,fromMoneyEdit: EditText)
            = recordFactory.create(fromDateSpinner, ::createFirstDate,type,fromMoneyEdit)

    fun createToRecord(toDateSpinner: Spinner,type: RecordType,toMoneyEdit: EditText)
            = recordFactory.create(toDateSpinner, ::createLastDate,type,toMoneyEdit)
}