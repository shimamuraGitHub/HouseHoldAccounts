package com.exsample.householdaccounts.controller.activity.history

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.*
import com.exsample.householdaccounts.R
import com.exsample.householdaccounts.controller.activity.find
import com.exsample.householdaccounts.controller.activity.getInflate
import com.exsample.householdaccounts.controller.activity.navigation.NavigationListener
import com.exsample.householdaccounts.controller.activity.removeFromParent
import com.exsample.householdaccounts.controller.widgets.list.HistoryTable
import com.exsample.householdaccounts.controller.widgets.setRecordTypeAdapter
import com.exsample.householdaccounts.db.DBOpenHelper
import com.exsample.householdaccounts.domain.record.RecordList
import com.exsample.householdaccounts.domain.type.RecordTypeList
import kotlinx.android.synthetic.main.activity_history.*

/**
 * Created by ryosuke on 2018/03/03.
 */
abstract class AbstractActivity : NavigationListener() {

    val dbHelper = DBOpenHelper(this, 1)
    val service by lazy { HistoryService(dbHelper) }

    /* 検索条件Layout */
    val layout by lazy { getInflate(R.layout.search_history) }

    val sumMoney by lazy { find<TextView>(R.id.sumMoney) }

    val historyTable by lazy { HistoryTable(find(R.id.historyTable)) }

    val typeSpinner by lazy { layout.find<Spinner>(R.id.historyTypeNames) }
    val fromDateSpinner by lazy { layout.find<Spinner>(R.id.fromDates) }
    val toDateSpinner by lazy { layout.find<Spinner>(R.id.toDates) }
    val fromMoneyEdit by lazy { layout.find<EditText>(R.id.fromMoney) }
    val toMoneyEdit by lazy { layout.find<EditText>(R.id.toMoney) }

    val search by lazy { layout.find<Button>(R.id.search) }

    /* 利用可能フラグに関わらない全てのレコードタイプ */
    val recordTypes by lazy { service.findAllRecordTypes() }

    /* 利用可能フラグが真(1)のもののみで構成されるレコードタイプList */
    val enabledRecordTypes by lazy { service.findAllEnabledTypes() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        buildList(service.findAllRecord())

        fab.setOnClickListener {

            // 複数回ボタンが押された場合、親要素(AlertDialog)との関係を切り離してから再度設定する
            layout.removeFromParent()
            AlertDialog.Builder(this).setView(layout).show()

            service.setSpinners(fromDateSpinner, toDateSpinner)
            typeSpinner.setRecordTypeAdapter(enabledRecordTypes)

            search.setOnClickListener { search() }
        }
    }

    /**
     * 合計金額、履歴テーブルを生成する.
     */
    abstract fun buildList(records: RecordList)

    /**
     * 登録日、金額、項目名を条件として対象となるレコードを検索し、
     * 履歴テーブルを再出力する.
     *
     */
    protected abstract fun search()
}