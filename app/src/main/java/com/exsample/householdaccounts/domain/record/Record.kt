package com.exsample.householdaccounts.domain.record

import com.exsample.householdaccounts.domain.type.RecordType
import com.exsample.householdaccounts.domain.type.RecordTypeList
import java.io.Serializable
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

    var recordType: RecordType? = null

    fun getTypeName() = recordType?.name

    fun isLatestType(typeList: RecordTypeList) = (recordType == typeList.findLatestRegistered(this))
}