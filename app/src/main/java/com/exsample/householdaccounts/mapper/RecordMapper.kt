package com.exsample.householdaccounts.mapper

import android.database.Cursor
import com.exsample.householdaccounts.db.DBOpenHelper
import com.exsample.householdaccounts.db.SQLiteExtendFuns
import com.exsample.householdaccounts.domain.Record
import java.sql.Date

/**
 * Created by ryosuke on 2018/02/05.
 */
class RecordMapper (val helper: DBOpenHelper) : SQLiteExtendFuns {

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

    fun findAll() = query(db = db,tableName = "RECORD")

    fun delete(record: Record) = db.delete("RECORD","ID = ?", arrayOf(record.id))

    fun search(fromRecord: Record,toRecord: Record): Cursor {
        val hasFromDate = if(fromRecord.date != null) 1 else 0
        val hasFromMoney = if(fromRecord.money != null) 1 else 0
        val hasType = if(fromRecord.type != null) 1 else 0
        val hasToDate = if(toRecord.date != null) 1 else 0
        val hasToMoney = if(toRecord.money != null) 1 else 0
        val count = hasFromDate + hasFromMoney + hasType + hasToDate + hasToMoney
        val sql = StringBuilder(" SELECT * FROM RECORD ")
        if (count > 0){
            sql.append(" WHERE MONEY > 0 ")
        }
        if(hasFromDate > 0){
            sql.append(" AND ")
            sql.append(" DATE > '${fromRecord.getAdjustedDate()}' ")
        }
        if(hasToDate > 0){
            sql.append(" AND ")
            sql.append(" DATE < '${toRecord.getAdjustedDate()}' ")
        }
        if(hasType > 0){
            sql.append(" AND ")
            sql.append(" TYPE_CODE = '${fromRecord.type}' ")
        }
        if(hasFromMoney > 0){
            sql.append(" AND ")
            sql.append(" MONEY >  ${fromRecord.money} ")
        }
        if(hasToMoney > 0){
            sql.append(" AND ")
            sql.append(" MONEY < ${toRecord.money} ")
        }
        return db.rawQuery(sql.toString(), arrayOf())
    }
}
