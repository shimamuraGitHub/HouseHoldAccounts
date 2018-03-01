package com.exsample.householdaccounts.domain

import com.exsample.householdaccounts.db.DBOpenHelper
import com.exsample.householdaccounts.mapper.RecordTypeMapper
import com.exsample.householdaccounts.util.parseHyphen
import com.exsample.householdaccounts.util.toBoolean

/**
 * Created by ryosuke on 2018/02/24.
 */
class RecordTypeRepository(helper: DBOpenHelper) {

    val mapper = RecordTypeMapper(helper)

    fun findAllEnabled() : RecordTypeList {

        val INDEX_CODE = 0
        val INDEX_NAME = 1
        val cursor = mapper.findAllEnabled()

        val list = mutableListOf<RecordType>()

        while(cursor.moveToNext()){
            val atended = cursor.getString(5)
            list.add(RecordType(cursor.getString(INDEX_CODE),
                    cursor.getString(INDEX_NAME),
                    cursor.getInt(2).toBoolean(),
                    cursor.getInt(3).toBoolean(),
                    parseHyphen(cursor.getString(4)),
                    if ( atended != null) parseHyphen(atended) else null
                    ))
        }
        return RecordTypeList(list)
    }

    fun update(recordType: RecordType) = mapper.update(recordType)

    fun toDisable(recordType: RecordType) = mapper.toDisable(recordType)

    fun insert(recordType: RecordType) = mapper.insert(recordType)

}