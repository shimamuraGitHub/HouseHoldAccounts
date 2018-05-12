package com.exsample.householdaccounts.domain.type

import com.exsample.householdaccounts.db.DBOpenHelper

/**
 * Created by ryosuke on 2018/03/10.
 */
class RecordTypeAgent(helper: DBOpenHelper) {

    private val repo = RecordTypeRepository(helper)

    /**
     * 利用可能フラグが真(1)のレコードタイプを全て取得する.
     */
    fun findAllEnabledTypes() = repo.findAllEnabled()

    /**
     * テーブル内の全てのレコードを取得する.
     */
    fun findAllTypes() = repo.findAll()

    /**
     * レコードタイプを更新する.
     */
    fun updateType(type: RecordType) = repo.update(type)

    /**
     * レコードタイプを利用不可に更新する.
     */
    fun toDisableType(type: RecordType) = repo.toDisable(type)

    /**
     * レコードタイプを登録する.
     */
    fun registerType(type: RecordType) = repo.insert(type)
}