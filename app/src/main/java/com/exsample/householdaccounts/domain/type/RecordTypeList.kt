package com.exsample.householdaccounts.domain.type

import com.exsample.householdaccounts.domain.record.Record
import com.exsample.householdaccounts.util.existsNext
import com.exsample.householdaccounts.util.toCode

/**
 * Created by ryosuke on 2018/02/11.
 */
class RecordTypeList(val list:List<RecordType>) {

    fun<T> map(func:(RecordType) -> T) = list.map(func)

    /**
     * 保持している支出項目の中で、引数が持つ項目名と同じ名前の最も遅く登録されたデータを探して取得する.
     * @param record レコード(支出項目.項目名)
     */
    fun findLatestRegistered(record: Record) = list
            .filter { it.name == record.recordType!!.name }
            .sortedBy { it.atStarted!!.time }
            .reversed()
            .first()

    /**
     * 登録済みのレコードから対応する支出項目情報を探し出し取得する.
     * @param record レコード(項目, 更新日)
     */
    fun findByRecord(record: Record) = list
            .filter { it.code.equals(record.type) }
            .filter{it.untilAtStarted(record)}
            .first {if(it.atEnded != null) it.afterAtEnded(record) else true}

    /**
     * 引数の項目名と同じ支出項目を探し出し取得する.
     * @param type 支出項目(項目名)
     */
    fun findByName(type: RecordType) = list.first { it.name == type.name }

    /**
     * 同名の項目情報が存在するか調べて結果を返却する.
     * @param type 支出項目(項目名)
     */
    fun existsEqualsName(type: RecordType) = list.any { it.name == type.name }

    /**
     * 同項目名かつ異なるコード値の支出項目が存在するか判定する.
     * @param type 支出項目(コード値, 項目名)
     */
    fun hasNameWithDifferCode(type: RecordType) = list.any { it.equalsNameWithDifferCode(type) }

    /**
     * 現在利用可能状態にないコード値の中で最低値を取得する.
     */
    fun findNotExistsMinCode() : String {
        val intCodes = list.map { it.code!!.toInt() }
        return (intCodes.sorted().first { !intCodes.existsNext(it) } + 1).toCode(3)
    }

    /**
     * コード値が等しい最初の支出項目を取得する.
     * @param record レコード(支出項目)
     */
    fun indexOfByCode(record: Record) = list.indexOfFirst { it.code == record.type }
}
