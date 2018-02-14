package com.exsample.householdaccounts.domain

import com.exsample.householdaccounts.db.DBOpenHelper
import com.exsample.householdaccounts.mapper.RecordMapper

/**
 * Created by ryosuke on 2018/02/08.
 */
class RecordAgent(helper:DBOpenHelper) {

    val repo = RecordRepository(helper)

    fun register(record:Record) = repo.insert(record)

    fun findAll() = repo.findAll()

    fun findAllTypes() = repo.findAllTypes()

    fun erase(record: Record) = repo.delete(record)

    fun search(recordA: Record,recordB: Record) = repo.search(recordA,recordB)
}