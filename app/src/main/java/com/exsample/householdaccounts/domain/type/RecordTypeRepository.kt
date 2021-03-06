package com.exsample.householdaccounts.domain.type

import android.database.Cursor
import com.exsample.householdaccounts.db.DBOpenHelper
import com.exsample.householdaccounts.db.mapper.RecordTypeMapper
import com.exsample.householdaccounts.domain.factory.RecordTypeFactory

/**
 * Created by ryosuke on 2018/02/24.
 */
class RecordTypeRepository(helper: DBOpenHelper) {

    private val mapper = RecordTypeMapper(helper)

    /**
     * 全てのレコードタイプを取得する.
     */
    fun findAll() = RecordTypeList(create(mapper.findAll()))

    /**
     * 利用可能なレコードタイプListを取得する.
     */
    fun findAllEnabled() = RecordTypeList(create(mapper.findAllEnabled()))

    /**
     * カーソルが指している行を、レコードタイプclassに格納し、
     * Listにして返却する.
     */
    private fun create(cursor: Cursor):List<RecordType>{
        val list = mutableListOf<RecordType>()

        while(cursor.moveToNext()){
            list.add(RecordTypeFactory.create(cursor))
        }
        return list
    }

    /**
     * レコードタイプを更新する.
     */
    fun update(recordType: RecordType) = mapper.update(recordType)

    /**
     * 該当するレコードタイプを使用不可にする.
     */
    fun toDisable(recordType: RecordType) = mapper.toDisable(recordType)

    /**
     * レコードタイプを登録する.
     */
    fun insert(recordType: RecordType) = mapper.insert(recordType)
}