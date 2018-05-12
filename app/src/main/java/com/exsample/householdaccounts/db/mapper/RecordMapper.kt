package com.exsample.householdaccounts.db.mapper

import android.content.ContentValues
import android.database.Cursor
import com.exsample.householdaccounts.db.DBOpenHelper
import com.exsample.householdaccounts.db.SQLiteExtendFun
import com.exsample.householdaccounts.domain.record.Record
import com.exsample.householdaccounts.util.toSQLString
import com.exsample.householdaccounts.util.toTimestamp

/**
 * Created by ryosuke on 2018/02/05.
 */
class RecordMapper (private val helper: DBOpenHelper) : SQLiteExtendFun {

    val db = helper.readableDatabase

    val ID = "ID"
    val DATE = "DATE"
    val MONEY = "MONEY"
    val TYPE_CODE = "TYPE_CODE"
    val UPDATED_AT = "UPDATED_AT"

    val TABLE_NAME = "RECORD"

    fun insert(record: Record):Int{
        val sql = """
            INSERT INTO RECORD
                (ID,DATE,TYPE_CODE,MONEY,CREATED_AT,UPDATED_AT)
            VALUES(
                '${record.id}',
                '${record.date!!.toTimestamp().toString()}',
                '${record.type}',
                 ${record.money},
                 '${record.createdAt!!.toTimestamp().toString()}',
                 '${record.updatedAt!!.toTimestamp().toString()}'

            )
        """.trimIndent()
        db.execSQL(sql)
        return 1
    }

    fun findAll() = query(db = db,tableName = "RECORD")

    fun delete(record: Record) = db.delete("RECORD","ID = ?", arrayOf(record.id))

    fun search(fromRecord: Record, toRecord: Record): Cursor {
        val hasFromDate = if(fromRecord.date != null) 1 else 0
        val hasFromMoney = if(fromRecord.money != null) 1 else 0
        val hasType = if(fromRecord.type != null) 1 else 0
        val hasToDate = if(toRecord.date != null) 1 else 0
        val hasToMoney = if(toRecord.money != null) 1 else 0
        val count = hasFromDate + hasFromMoney + hasType + hasToDate + hasToMoney
        val sql = StringBuilder(" SELECT * FROM RECORD R")
        sql.append(" INNER JOIN RECORD_TYPE RT ")
        sql.append(" ON R.TYPE_CODE = RT.CODE")
        sql.append(" AND RT.AT_STARTED <= R.UPDATED_AT")
        sql.append(" AND ifnull(RT.AT_ENDED,'9999-12-31 23:59:59.9') > R.UPDATED_AT")
        if (count > 0){
            sql.append(" WHERE R.MONEY > 0 ")
        }
        if(hasFromDate > 0){
            sql.append(" AND ")
            sql.append(" R.DATE > '${fromRecord.date!!.toSQLString()}' ")
        }
        if(hasToDate > 0){
            sql.append(" AND ")
            sql.append(" R.DATE < '${toRecord.date!!.toSQLString()}' ")
        }
        if(hasType > 0){
            sql.append(" AND ")
            sql.append(" RT.NAME = '${fromRecord.getTypeName()}' ")
        }
        if(hasFromMoney > 0){
            sql.append(" AND ")
            sql.append(" R.MONEY >  ${fromRecord.money} ")
        }
        if(hasToMoney > 0){
            sql.append(" AND ")
            sql.append(" R.MONEY < ${toRecord.money} ")
        }
        sql.append(" ORDER BY R.DATE ")
        return db.rawQuery(sql.toString(), arrayOf())
    }

    fun update(record: Record):Int{
        val values = ContentValues()
        values.put(DATE, record.date!!.toSQLString())
        values.put(MONEY, record.money)
        values.put(TYPE_CODE, record.type)
        values.put(UPDATED_AT, record.updatedAt!!.toTimestamp().toString())

        return update(db,TABLE_NAME,values,"ID = ?",arrayOf(record.id!!))
    }
}
