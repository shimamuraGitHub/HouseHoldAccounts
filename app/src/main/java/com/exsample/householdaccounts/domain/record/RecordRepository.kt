package com.exsample.householdaccounts.domain.record

import android.database.Cursor
import com.exsample.householdaccounts.db.DBOpenHelper
import com.exsample.householdaccounts.domain.factory.RecordFactory
import com.exsample.householdaccounts.db.mapper.RecordMapper

/**
 * Created by ryosuke on 2018/02/10.
 */
class RecordRepository(helper: DBOpenHelper) {

    private val mapper = RecordMapper(helper)

    /**
     * レコードを登録する.
     */
    fun insert(record: Record) = mapper.insert(record)

    /**
     * 検索条件に合致するレコードを取得する.
     */
    fun search(from: Record, to: Record) = createRecordList(mapper.search(from,to))

    /**
     * 検索結果をcursorからListに変換する.
     */
    private fun createRecordList(cursor: Cursor) : RecordList {

        val list = mutableListOf<Record>()

        while(cursor.moveToNext()){
            list.add(RecordFactory.create(cursor))
        }
        return RecordList(list)
    }

    /**
     * IDに紐づくレコードを削除する.
     */
    fun delete(record: Record) = mapper.delete(record)

    /**
     * IDに紐づくレコードを更新する.
     */
    fun update(record: Record) = mapper.update(record)

}