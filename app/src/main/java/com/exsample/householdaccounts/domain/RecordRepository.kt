package com.exsample.householdaccounts.domain

import android.content.ContentValues
import android.database.Cursor
import com.exsample.householdaccounts.db.DBOpenHelper
import com.exsample.householdaccounts.mapper.RecordMapper
import com.exsample.householdaccounts.mapper.RecordTypeMapper
import com.exsample.householdaccounts.util.parseHyphen

/**
 * Created by ryosuke on 2018/02/10.
 */
class RecordRepository(helper: DBOpenHelper) {

    val mapper = RecordMapper(helper)

    fun insert(record: Record) = mapper.insert(record)

    fun findAll() = buildRecordList(mapper.findAll())

    fun search(fromRecord: Record, toRecord: Record) = buildRecordList(mapper.search(fromRecord,toRecord))

    private fun buildRecordList(cursor: Cursor) : RecordList {

        val INDEX_ID = 0
        val INDEX_DATE = 1
        val INDEX_TYPECODE = 2
        val INDEX_MONEY = 3

        val list = mutableListOf<Record>()

        while(cursor.moveToNext()){
            list.add(
                    Record(cursor.getString(INDEX_ID),
                            parseHyphen(cursor.getString(INDEX_DATE)),
                            cursor.getString(INDEX_TYPECODE),
                            cursor.getInt(INDEX_MONEY))
            )
        }
        return RecordList(list)
    }

    fun delete(record: Record) = mapper.delete(record)

}