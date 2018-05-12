package com.exsample.householdaccounts.domain.record

import com.exsample.householdaccounts.db.DBOpenHelper

/**
 * Created by ryosuke on 2018/02/08.
 */
class RecordAgent(helper:DBOpenHelper) {

    private val repo = RecordRepository(helper)

    /**
     * レコードを登録する.
     */
    fun register(record: Record) = repo.insert(record)

    /**
     * IDに紐づくレコードを削除する.
     */
    fun erase(record: Record) = repo.delete(record)

    /**
     * IDに紐づくレコードを更新する.
     */
    fun update(record: Record) = repo.update(record)

    /**
     * 検索条件に合致するレコードListを取得する.
     */
    fun search(from: Record, to: Record) = repo.search(from, to)
}