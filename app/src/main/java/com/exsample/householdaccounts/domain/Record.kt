package com.exsample.householdaccounts.domain

import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by ryosuke on 2018/01/21.
 */
data class Record(
        val id : String? = null,
        val date : Date? = null,
        val type: String? = null,
        val money : Int? = null
):Serializable
{
    private val serialVersionUID = 3820984320L

    var recordType:RecordType? = null

    fun getAdjustedDate() : String{
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        return sdf.format(date)
    }

    fun toStringMoney() = money.toString()

    fun getTypeName() = recordType?.name
}