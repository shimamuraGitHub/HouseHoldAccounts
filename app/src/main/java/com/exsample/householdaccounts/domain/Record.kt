package com.exsample.householdaccounts.domain

import com.exsample.householdaccounts.util.toSQLString
import java.io.Serializable
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by ryosuke on 2018/01/21.
 */
data class Record(
        val id : String? = null,
        val date : Date? = null,
        val type: String? = null,
        val money : Int? = null,
        val createdAt:Date? = null,
        val updatedAt:Date? = null

):Serializable
{
    private val serialVersionUID = 3820984320L

    var recordType:RecordType? = null

    fun toStringMoney() = money.toString()

    fun getTypeName() = recordType?.name

    fun isLatestType(typeList: RecordTypeList) = recordType!!.equals(typeList.findLatest(this))
}