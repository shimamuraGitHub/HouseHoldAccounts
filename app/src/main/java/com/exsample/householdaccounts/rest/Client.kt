package com.exsample.householdaccounts.rest

import com.exsample.householdaccounts.domain.Type

/**
 * Created by ryosuke on 2018/01/22.
 */
class Client{
    fun send(item: Type): Boolean {
        return true
    }

    fun findItemList():List<Type>{
        return listOf<Type>(Type("001","収入"), Type("002","外食費"), Type("003","娯楽費"))
    }
}