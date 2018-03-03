package com.exsample.householdaccounts.domain

import android.content.ContentValues
import android.database.Cursor
import com.exsample.householdaccounts.db.DBOpenHelper
import com.exsample.householdaccounts.mapper.RecordMapper
import com.exsample.householdaccounts.util.toDate

/**
 * Created by ryosuke on 2018/02/10.
 */
class RecordRepository(helper: DBOpenHelper) {

    val mapper = RecordMapper(helper)

    fun insert(record: Record) = mapper.insert(record)

    fun findAll() = buildRecordList(mapper.findAll())

    fun search(fromRecord: Record, toRecord: Record) = buildRecordList(mapper.search(fromRecord,toRecord))

    private fun buildRecordList(cursor: Cursor) : RecordList {

        val list = mutableListOf<Record>()

        while(cursor.moveToNext()){
            list.add(
                Record(
                    cursor.getString(0),                          // ID
                    cursor.getString(1).toDate(),                 // DATE
                    cursor.getString(2),                          // TYPE_CODE
                    cursor.getInt(3)                              // MONEY
                )
            )
        }
        return RecordList(list)
    }

    fun delete(record: Record) = mapper.delete(record)

}