package com.exsample.householdaccounts.domain

import com.exsample.householdaccounts.db.DBOpenHelper

/**
 * Created by ryosuke on 2018/02/08.
 */
class RecordAgent(helper:DBOpenHelper) {

    val repo = RecordRepository(helper)
    val typeRepo = RecordTypeRepository(helper)

    fun register(record:Record) = repo.insert(record)

    fun findAll() = repo.findAll()

    fun findAllEnabledTypes() = typeRepo.findAllEnabled()

    fun erase(record: Record) = repo.delete(record)

    fun search(fromRecord: Record, toRecord: Record) = repo.search(fromRecord, toRecord)

    fun updateType(recordType: RecordType) = typeRepo.update(recordType)

    fun toDisableType(recordType: RecordType) = typeRepo.toDisable(recordType)

    fun registerType(recordType: RecordType) = typeRepo.insert(recordType)
}