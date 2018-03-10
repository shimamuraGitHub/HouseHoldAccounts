package com.exsample.householdaccounts.db

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase

/**
 * Created by ryosuke on 2018/03/02.
 */
interface SQLiteExtendFun {
    fun query(
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

    fun delete(
            db : SQLiteDatabase,
            tableName:String,
            whereClause:String? = null,
            whereArgs:Array<String>? = null
    ) = db.delete(tableName,whereClause,whereArgs)

    fun update(
            db: SQLiteDatabase,
            tableName: String,
            values: ContentValues,
            whereClause: String? = null,
            whereArgs: Array<String>? = null
    ) = db.update(tableName,values,whereClause,whereArgs)
}