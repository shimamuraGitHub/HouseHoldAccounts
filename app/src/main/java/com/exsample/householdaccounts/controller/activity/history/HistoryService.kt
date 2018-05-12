package com.exsample.householdaccounts.controller.activity.history

import android.widget.EditText
import android.widget.Spinner
import com.exsample.householdaccounts.controller.widgets.Year_MonthSpinnerFunctions
import com.exsample.householdaccounts.controller.widgets.setSearchDateAdapter
import com.exsample.householdaccounts.domain.factory.RecordFactory
import com.exsample.householdaccounts.db.DBOpenHelper
import com.exsample.householdaccounts.domain.factory.RecordTypeFactory
import com.exsample.householdaccounts.domain.record.Record
import com.exsample.householdaccounts.domain.record.RecordAgent
import com.exsample.householdaccounts.domain.type.RecordType
import com.exsample.householdaccounts.domain.type.RecordTypeAgent
import com.exsample.householdaccounts.domain.type.RecordTypeList
import com.exsample.householdaccounts.util.getMonth
import java.util.*

/**
 * Created by ryosuke on 2018/02/11.
 */
class HistoryService(helper: DBOpenHelper) : Year_MonthSpinnerFunctions {

    private val recordAgent = RecordAgent(helper)
    private val typeAgent = RecordTypeAgent(helper)

    /**
     * テーブル内の全てのレコードを取得する.
     */
    fun findAllRecord() = recordAgent.search(Record(), Record())

    /**
     * 利用可能フラグが真(1)のレコードタイプを全て取得する.
     */
    fun findAllEnabledTypes() = typeAgent.findAllEnabledTypes()

    /**
     * テーブル内の全てのレコードを取得する.
     */
    fun findAllRecordTypes() = typeAgent.findAllTypes()

    /**
     * レコードタイプList内の、スピナーの項目名要素と一致するレコードタイプを特定し返却する.
     */
    fun findType(recordTypes: RecordTypeList, typeSpinner: Spinner)
             = recordTypes.findByName(RecordTypeFactory.create(typeSpinner))

    /**
     * IDが一致するレコードをテーブルから削除する.
     */
    fun erase(target: Record) = recordAgent.erase(target)

    /**
     * 検索条件に合致するレコードListを取得する.
     */
    fun search(from: Record, to: Record) = recordAgent.search(from, to)

    /**
     * 入力項目から、検索条件レコードを生成する(～から、～以上).
     */
    fun createFromRecord(fromDate: Spinner, type: RecordType, fromMoney: EditText)
            = RecordFactory.create(fromDate.createSelectedDateAtFirst(),type, fromMoney)

    /**
     * 入力項目から、検索条件レコードを生成する(～まで、～以下).
     */
    fun createToRecord(toDate: Spinner, type: RecordType, toMoney: EditText)
            = RecordFactory.create(toDate.createSelectedDateAtLast(),type, toMoney)

    /**
     * Dateスピナーの初期設定を行う.
     * 設定項目：要素
     *           初期表示要素
     */
    fun setSpinners(fromDateSpinner:Spinner,toDateSpinner:Spinner){

        fun setSpinner(dateSpinner: Spinner,selection : Int){
            dateSpinner.setSearchDateAdapter()
            dateSpinner.setSelection(selection)
        }

        val thisMonth = Calendar.getInstance().getMonth()
        setSpinner(fromDateSpinner,24 + thisMonth)
        setSpinner(toDateSpinner,25 + thisMonth)
    }
}