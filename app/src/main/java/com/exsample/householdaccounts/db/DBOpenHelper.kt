package com.exsample.householdaccounts.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by ryosuke on 2018/02/06.
 */
class DBOpenHelper(
        context: Context?,
        version: Int,
        name: String? = "houseHoldAccount",
        factory: SQLiteDatabase.CursorFactory? = null
) : SQLiteOpenHelper(context, name, factory, version) ,SQLiteExtendFun{

    override fun onCreate(db: SQLiteDatabase) {

        /* (危険！)全てのテーブルをDROPしたいときに */
//        dropAllTable.forEach { db.execSQL(it) }
        // RECORD_TYPEのカラム構造を変更したときに
//        db.execSQL("DROP TABLE RECORD_TYPE")

//        db.execSQL("DELETE FROM RECORD WHERE MONEY = 99999")

        db.execSQL(createRecord)
        db.execSQL(createType)


        /* RECORD_TYPEの初期コード値を変更したいときに */
//        delete(db=db,tableName = "RECORD_TYPE")
//        insertTypes.forEach { db.execSQL(it) }

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}
}
