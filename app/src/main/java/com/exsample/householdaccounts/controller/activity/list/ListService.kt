package com.exsample.householdaccounts.controller.activity.list

import com.exsample.householdaccounts.db.DBOpenHelper
import com.exsample.householdaccounts.domain.Record
import com.exsample.householdaccounts.domain.RecordAgent

/**
 * Created by ryosuke on 2018/02/11.
 */
class ListService(helper: DBOpenHelper) {

    val recordAgent = RecordAgent(helper)

    fun findAllRecord() = recordAgent.findAll()

    fun findAllRecordTypes() = recordAgent.findAllTypes()

    fun erase(record: Record) = recordAgent.erase(record)

    fun search(recordA: Record,recordB: Record) = recordAgent.search(recordA,recordB)
}