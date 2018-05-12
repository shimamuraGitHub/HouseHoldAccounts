package com.exsample.householdaccounts.domain.type

import com.exsample.householdaccounts.domain.record.Record
import com.exsample.householdaccounts.util.until
import java.io.Serializable
import java.util.*

/**
 * Created by ryosuke on 2018/01/21.
 */
data class RecordType(
        val code: String? = null,
        val name: String? = null,
        val isExpenditure: Boolean? = null,
        val enabled: Boolean? = null,
        val atStarted: Date? = null,
        val atEnded: Date? = null
) : Serializable {
    private val serialVersionUID = 3820984323240L

    /**
     * 同項目名かつ異なるコード値の支出項目か否か判定する.
     */
    fun equalsNameWithDifferCode(type: RecordType) = (!equalsCode(type) && equalsName(type))

    /**
     * 同名の支出項目か判定する.
     */
    private fun equalsName(type: RecordType) = this.name == type.name

    /**
     * 同じコード値の支出項目化か判定する.
     */
    private fun equalsCode(type: RecordType) = this.code == type.code

    fun untilAtStarted(record: Record) = (atStarted != null) && (atStarted.until(record.updatedAt!!))

    fun afterAtEnded(record: Record) = (atEnded != null) && (atEnded.after(record.updatedAt))
}