package com.exsample.householdaccounts.domain.record

import com.exsample.householdaccounts.db.DBOpenHelper

/**
 * Created by ryosuke on 2018/02/08.
 */
class RecordAgent(helper:DBOpenHelper) {

    private val repo = RecordRepository(helper)

    fun register(record: Record) = repo.insert(record)

    fun erase(record: Record) = repo.delete(record)

    fun update(record: Record) = repo.update(record)

    fun search(from: Record, to: Record) = repo.search(from, to)
}