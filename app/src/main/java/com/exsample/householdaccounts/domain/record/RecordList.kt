package com.exsample.householdaccounts.domain.record

/**
 * Created by ryosuke on 2018/02/17.
 */
class RecordList(val list:List<Record>) {
    /**
     * List#forEach.
     */
    fun forEach(body:(Record) -> Unit) = list.forEach(body)
}