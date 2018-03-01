package com.exsample.householdaccounts.domain

import java.util.*

/**
 * Created by ryosuke on 2018/01/21.
 */
data class RecordType(
        val code : String? = null,
        val name : String? = null,
        val isExpenditure : Boolean? = null,
        val enabled : Boolean? = null,
        val atStarted:Date? = null,
        val atEnded:Date? = null
){
    fun equalsNameWithDifferCode(type:RecordType) = (!(equalsCode(type)) && equalsName(type))

    fun equalsName(type:RecordType) = this.name!!.equals(type.name)

    fun equalsCode(type:RecordType) = this.code!!.equals(type.code)

}