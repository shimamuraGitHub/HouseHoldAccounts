package com.exsample.householdaccounts.domain

import com.exsample.householdaccounts.db.DBOpenHelper

/**
 * Created by ryosuke on 2018/02/08.
 */
class RecordAgent(helper:DBOpenHelper) {

    val repo = RecordRepository(helper)

    fun register(record:Record) = repo.insert(record)

    fun findAll() = repo.findAll()

    fun findAllTypes() = repo.findAllTypes()

    fun erase(record: Record) = repo.delete(record)

    fun search(fromRecord: Record, toRecord: Record) = repo.search(fromRecord, toRecord)
}