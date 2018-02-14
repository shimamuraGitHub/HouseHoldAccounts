package com.exsample.householdaccounts.mapper

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.exsample.householdaccounts.db.DBOpenHelper
import com.exsample.householdaccounts.db.dbQuery
import com.exsample.householdaccounts.domain.Record
import java.sql.Date

/**
 * Created by ryosuke on 2018/02/05.
 */
class RecordMapper (val helper: DBOpenHelper){

    val db = helper.readableDatabase

    fun insert(record:Record):Int{
        val sql = """
            INSERT INTO RECORD
                (ID,DATE,TYPE_CODE,MONEY)
            VALUES(
                '${record.id}',
                '${Date(record.date!!.time)}',
                '${record.type}',
                 ${record.money}
            )
        """.trimIndent()
        db.execSQL(sql)
        return 1
    }

    fun findAll() = dbQuery(db = db,tableName = "RECORD")

    fun delete(record: Record) = db.delete("RECORD","ID = ?", arrayOf(record.id))
}
