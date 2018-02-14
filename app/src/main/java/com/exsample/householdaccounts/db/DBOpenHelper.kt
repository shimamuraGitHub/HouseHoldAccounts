package com.exsample.householdaccounts.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by ryosuke on 2018/02/06.
 */
class DBOpenHelper(
        context: Context?,
        name: String? = "houseHoldAccount",
        factory: SQLiteDatabase.CursorFactory? = null,
        version: Int
) : SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(db: SQLiteDatabase?) {

        if(db == null) return

        /* テーブル設定を変更したときに */
//        dropAllTable.forEach { db.execSQL(it) }

        db.execSQL(createRecord)
        db.execSQL(createType)

        if(dbQuery(db=db,tableName="RECORD_TYPE").count != 9){
            dbDelete(db=db,tableName = "RECORD_TYPE")
            insertTypes.forEach { db.execSQL(it) }
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}
}

fun dbQuery(
        db : SQLiteDatabase,
        tableName:String,
        columns:Array<String>? = null,
        selection:String = "",
        selectionArgs:Array<String> = arrayOf<String>(),
        groupBy:String = "",
        having:String = "",
        orderBy:String = "",
        limit:String = ""
) = db.query(tableName,columns,selection,selectionArgs,groupBy,having,orderBy,limit)

fun dbDelete(
        db : SQLiteDatabase,
        tableName:String,
        whereClause:String? = null,
        whereArgs:Array<String>? = null
) = db.delete(tableName,whereClause,whereArgs)