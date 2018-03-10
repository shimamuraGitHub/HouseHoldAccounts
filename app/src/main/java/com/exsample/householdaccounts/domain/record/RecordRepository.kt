package com.exsample.householdaccounts.domain.record

import android.database.Cursor
import com.exsample.householdaccounts.db.DBOpenHelper
import com.exsample.householdaccounts.domain.factory.RecordFactory
import com.exsample.householdaccounts.db.mapper.RecordMapper

/**
 * Created by ryosuke on 2018/02/10.
 */
class RecordRepository(helper: DBOpenHelper) {

    private val mapper = RecordMapper(helper)

    fun insert(record: Record) = mapper.insert(record)

    fun search(from: Record, to: Record) = createRecordList(mapper.search(from,to))

    private fun createRecordList(cursor: Cursor) : RecordList {

        val list = mutableListOf<Record>()

        while(cursor.moveToNext()){
            list.add(RecordFactory.create(cursor))
        }
        return RecordList(list)
    }

    fun delete(record: Record) = mapper.delete(record)

    fun update(record: Record) = mapper.update(record)

}