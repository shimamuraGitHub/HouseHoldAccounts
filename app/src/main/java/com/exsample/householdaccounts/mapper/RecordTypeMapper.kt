package com.exsample.householdaccounts.mapper

import android.content.ContentValues
import com.exsample.householdaccounts.db.DBOpenHelper
import com.exsample.householdaccounts.db.dbQuery
import com.exsample.householdaccounts.db.dbUpdate
import com.exsample.householdaccounts.domain.RecordType
import com.exsample.householdaccounts.util.toInt
import com.exsample.householdaccounts.util.toTimestampString
import java.sql.Timestamp
import java.util.*

/**
 * Created by ryosuke on 2018/02/08.
 */
class RecordTypeMapper(val helper: DBOpenHelper) {

    val db = helper.readableDatabase

    val TABLE_NAME = "RECORD_TYPE"
    val CODE = "CODE"
    val NAME = "NAME"
    val IS_EXPENDITURE = "IS_EXPENDITURE"
    val ENABLED = "ENABLED"
    val AT_STARTED = "AT_STARTED"
    val AT_ENDED = "AT_ENDED"

    fun findAllEnabled() = dbQuery(db=db, tableName = TABLE_NAME, selection = "ENABLED = 1")

    fun update(recordType: RecordType):Int{
        val values = ContentValues()
        values.put(CODE,recordType.code)
        values.put(NAME,recordType.name)
        values.put(IS_EXPENDITURE,recordType.isExpenditure!!.toInt())

        val clause = "CODE = ? AND ENABLED = ?"

        val args = arrayOf(recordType.code!!,recordType.enabled!!.toInt().toString())

        return dbUpdate(db,TABLE_NAME,values,clause,args)
    }

    fun toDisable(recordType: RecordType):Int{
        val values = ContentValues()
        values.put(ENABLED, 0)
        values.put(AT_ENDED,Date().toTimestampString())
        val clause = "CODE = ? AND ENABLED = 1"

        val args = arrayOf(recordType.code!!)

        return dbUpdate(db,TABLE_NAME,values,clause,args)
    }

    fun insert(recordType: RecordType):Int{
        val values = ContentValues()
        values.put(CODE,recordType.code)
        values.put(NAME,recordType.name)
        values.put(IS_EXPENDITURE,recordType.isExpenditure!!.toInt())
        values.put(ENABLED,recordType.enabled!!.toInt())
        values.put(AT_STARTED, Timestamp(recordType.atStarted!!.time).toString())
        return db.insert(TABLE_NAME,"AT_ENDED",values).toInt()
    }
}