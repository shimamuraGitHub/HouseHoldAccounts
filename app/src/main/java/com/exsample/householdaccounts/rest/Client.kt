package com.exsample.householdaccounts.rest

import com.exsample.householdaccounts.domain.RecordType

/**
 * Created by ryosuke on 2018/01/22.
 */
class Client{
    fun send(item: RecordType): Boolean {
        return true
    }

    fun findItemList():List<RecordType>{
        return listOf<RecordType>(RecordType("001","収入"), RecordType("002","外食費"), RecordType("003","娯楽費"))
    }
}