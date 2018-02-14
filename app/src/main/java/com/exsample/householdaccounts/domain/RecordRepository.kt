package com.exsample.householdaccounts.domain

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

    fun findAll() : List<Record>{
        val cursor = mapper.findAll()
        val list = mutableListOf<Record>()
        while(cursor.moveToNext()){
            list.add(
                    Record(cursor.getString(COLUMNSINDEX_RECORD_ID),
                            parseHyphen(cursor.getString(COLUMNSINDEX_RECORD_DATE)),
                            cursor.getString(COLUMNSINDEX_RECORD_TYPECODE),
                            cursor.getInt(COLUMNSINDEX_RECORD_MONEY))
            )
        }
        return list
    }

    fun findAllTypes() : List<RecordType>{

        val cursor = typeMapper.findAll()

        val list = mutableListOf<RecordType>()

        while(cursor.moveToNext()){
            list.add(RecordType(cursor.getString(COLUMNSINDEX_RECORD_TYPE_CODE),
                                  cursor.getString(COLUMNSINDEX_RECORD_TYPE_NAME)))
        }
        return list
    }

    fun delete(record: Record) = mapper.delete(record)

    fun search(recordA: Record,recordB: Record) = listOf<Record>()
}