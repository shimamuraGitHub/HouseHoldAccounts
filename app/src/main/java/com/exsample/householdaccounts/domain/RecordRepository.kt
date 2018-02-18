package com.exsample.householdaccounts.domain

import android.database.Cursor
import com.exsample.householdaccounts.db.DBOpenHelper
import com.exsample.householdaccounts.mapper.RecordMapper
import com.exsample.householdaccounts.mapper.RecordTypeMapper
import com.exsample.householdaccounts.util.parseHyphen

/**
 * Created by ryosuke on 2018/02/10.
 */
class RecordRepository(helper: DBOpenHelper) {

    val COLUMNSINDEX_RECORD_ID = 0
    val COLUMNSINDEX_RECORD_DATE = 1
    val COLUMNSINDEX_RECORD_TYPECODE = 2
    val COLUMNSINDEX_RECORD_MONEY = 3

    val COLUMNSINDEX_RECORD_TYPE_CODE = 0
    val COLUMNSINDEX_RECORD_TYPE_NAME = 1

    val mapper = RecordMapper(helper)

    val typeMapper = RecordTypeMapper(helper)

    fun insert(record: Record) = mapper.insert(record)

    fun findAll() :RecordList{
        val cursor = mapper.findAll()
        return buildRecordList(cursor)
    }

    private fun buildRecordList(cursor: Cursor) : RecordList {
        val list = mutableListOf<Record>()
        while(cursor.moveToNext()){
            list.add(
                    Record(cursor.getString(COLUMNSINDEX_RECORD_ID),
                            parseHyphen(cursor.getString(COLUMNSINDEX_RECORD_DATE)),
                            cursor.getString(COLUMNSINDEX_RECORD_TYPECODE),
                            cursor.getInt(COLUMNSINDEX_RECORD_MONEY))
            )
        }
        return RecordList(list)
    }

    fun findAllTypes() : RecordTypeList {

        val cursor = typeMapper.findAll()

        val list = mutableListOf<RecordType>()

        while(cursor.moveToNext()){
            list.add(RecordType(cursor.getString(COLUMNSINDEX_RECORD_TYPE_CODE),
                                  cursor.getString(COLUMNSINDEX_RECORD_TYPE_NAME)))
        }
        return RecordTypeList(list)
    }

    fun delete(record: Record) = mapper.delete(record)

    fun search(fromRecord: Record, toRecord: Record): RecordList {
        val cursor = mapper.search(fromRecord,toRecord)
        return buildRecordList(cursor)
    }
}