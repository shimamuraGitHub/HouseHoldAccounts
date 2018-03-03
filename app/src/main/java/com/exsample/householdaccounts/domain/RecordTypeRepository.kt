package com.exsample.householdaccounts.domain

import android.database.Cursor
import com.exsample.householdaccounts.db.DBOpenHelper
import com.exsample.householdaccounts.mapper.RecordTypeMapper
import com.exsample.householdaccounts.util.toBoolean
import com.exsample.householdaccounts.util.toDate

/**
 * Created by ryosuke on 2018/02/24.
 */
class RecordTypeRepository(helper: DBOpenHelper) {

    val mapper = RecordTypeMapper(helper)

    fun findAllEnabled() : RecordTypeList {

        val cursor = mapper.findAllEnabled()
        val list = mutableListOf<RecordType>()

        while(cursor.moveToNext()){
            list.add(create(cursor))
        }
        return RecordTypeList(list)
    }

    private fun create(cursor: Cursor): RecordType {
        val type = RecordType(
            cursor.getString(0),                                         // CODE
            cursor.getString(1),                                         // NAME
            cursor.getInt(2)?.toBoolean(),                               // IS_EXPENDITURE
            cursor.getInt(3)?.toBoolean(),                               // ENABLED
            cursor.getString(4)?.toDate(),                               // AT_STARTED
            cursor.getString(5)?.toDate()                                // AT_ENDED
        )
        return type
    }

    fun update(recordType: RecordType) = mapper.update(recordType)

    fun toDisable(recordType: RecordType) = mapper.toDisable(recordType)

    fun insert(recordType: RecordType) = mapper.insert(recordType)

}