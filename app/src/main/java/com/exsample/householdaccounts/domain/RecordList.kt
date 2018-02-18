package com.exsample.householdaccounts.domain

/**
 * Created by ryosuke on 2018/02/17.
 */
class RecordList(val list:List<Record>) {
    fun forEach(body:(Record) -> Unit) = list.forEach(body)
}