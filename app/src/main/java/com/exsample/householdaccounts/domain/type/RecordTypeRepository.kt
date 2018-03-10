package com.exsample.householdaccounts.domain.type

import android.database.Cursor
import com.exsample.householdaccounts.db.DBOpenHelper
import com.exsample.householdaccounts.db.mapper.RecordTypeMapper
import com.exsample.householdaccounts.domain.factory.RecordTypeFactory

/**
 * Created by ryosuke on 2018/02/24.
 */
class RecordTypeRepository(helper: DBOpenHelper) {

    private val mapper = RecordTypeMapper(helper)

    fun findAll() = RecordTypeList(create(mapper.findAll()))

    fun findAllEnabled() = RecordTypeList(create(mapper.findAllEnabled()))

    private fun create(cursor: Cursor):List<RecordType>{
        val list = mutableListOf<RecordType>()

        while(cursor.moveToNext()){
            list.add(RecordTypeFactory.create(cursor))
        }
        return list
    }

    fun update(recordType: RecordType) = mapper.update(recordType)

    fun toDisable(recordType: RecordType) = mapper.toDisable(recordType)

    fun insert(recordType: RecordType) = mapper.insert(recordType)
}