package com.exsample.householdaccounts.mapper

import android.content.ContentValues
import com.exsample.householdaccounts.db.DBOpenHelper
import com.exsample.householdaccounts.db.SQLiteExtendFuns
import com.exsample.householdaccounts.domain.RecordType
import com.exsample.householdaccounts.util.toInt
import com.exsample.householdaccounts.util.toTimestamp
import java.util.*

/**
 * Created by ryosuke on 2018/02/08.
 */
class RecordTypeMapper(val helper: DBOpenHelper): SQLiteExtendFuns {

    val db = helper.readableDatabase

    val TABLE_NAME = "RECORD_TYPE"
    val CODE = "CODE"
    val NAME = "NAME"
    val IS_EXPENDITURE = "IS_EXPENDITURE"
    val ENABLED = "ENABLED"
    val AT_STARTED = "AT_STARTED"
    val AT_ENDED = "AT_ENDED"

    fun findAllEnabled() = query(db=db, tableName = TABLE_NAME, selection = "ENABLED = 1")

    fun findAll() = query(db=db, tableName = TABLE_NAME)

    fun update(type: RecordType):Int{

        val values = ContentValues()
        values.put(CODE, type.code)
        values.put(NAME, type.name)
        values.put(IS_EXPENDITURE, type.isExpenditure!!.toInt())

        val clause = "CODE = ? AND ENABLED = ?"

        val args = arrayOf(type.code!!, type.enabled!!.toInt().toString())

        return update(db,TABLE_NAME,values,clause,args)
    }

    fun toDisable(type: RecordType):Int{

        val values = ContentValues()
        values.put(ENABLED, 0)
        values.put(AT_ENDED,Date().toTimestamp().toString())

        return update(db,TABLE_NAME,values,"CODE = ? AND ENABLED = 1",arrayOf(type.code!!))
    }

    fun insert(type: RecordType):Int{

        val values = ContentValues()
        values.put(CODE, type.code)
        values.put(NAME, type.name)
        values.put(IS_EXPENDITURE, type.isExpenditure!!.toInt())
        values.put(ENABLED, type.enabled!!.toInt())
        values.put(AT_STARTED, type.atStarted!!.toTimestamp().toString())

        return db.insert(TABLE_NAME,"AT_ENDED",values).toInt()
    }
}