package com.exsample.householdaccounts.mapper

import com.exsample.householdaccounts.db.DBOpenHelper
import com.exsample.householdaccounts.db.dbQuery

/**
 * Created by ryosuke on 2018/02/08.
 */
class RecordTypeMapper(val helper: DBOpenHelper) {

    val db = helper.readableDatabase

    val TABLE_NAME = "RECORD_TYPE"

    fun findAll() = dbQuery(db=db,tableName = TABLE_NAME)
}