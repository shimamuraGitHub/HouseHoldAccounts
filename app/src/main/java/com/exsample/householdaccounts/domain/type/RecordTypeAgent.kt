package com.exsample.householdaccounts.domain.type

import com.exsample.householdaccounts.db.DBOpenHelper

/**
 * Created by ryosuke on 2018/03/10.
 */
class RecordTypeAgent(helper: DBOpenHelper) {

    private val repo = RecordTypeRepository(helper)

    fun findAllEnabledTypes() = repo.findAllEnabled()

    fun findAllTypes() = repo.findAll()

    fun updateType(type: RecordType) = repo.update(type)

    fun toDisableType(type: RecordType) = repo.toDisable(type)

    fun registerType(type: RecordType) = repo.insert(type)
}