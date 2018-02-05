package com.exsample.householdaccounts

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.*
import com.exsample.householdaccounts.domain.Type
import com.exsample.householdaccounts.domain.Record
import com.exsample.householdaccounts.rest.Client
import java.util.*

class MainActivity : AppCompatActivity() {

    val client = Client()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val itemList = findViewById<View>(R.id.itemNames) as Spinner
        itemList.adapter = buildArrayAdapter(listOf("収入","外食費","娯楽費"))
    }

    /**
     * 登録ボタン押下時の処理.
     *
     */
    fun register(v: View){

        val money = (findViewById<View>(R.id.moneyAmount) as EditText).text.toString()

        if(money.isNullOrBlank()){
            buildAlert("Error","金額が入力されていません")
            return
        }

        val itemName = (findViewById<View>(R.id.itemNames) as Spinner).selectedItem as String
        val itemList = client.findItemList()
        val item = itemList.filter { it.name.equals(itemName) }.first()

        val record = Record(money.toInt(), item.id, Date())

        clear(v)
        if(client.send(item)) {
            buildMessage(record,item).show()
            return
        }

        buildAlert("Error","登録に失敗しました。")
    }

    /**
     * 登録成功時のToast作成メソッド.
     */
    private fun buildMessage(record: Record, item: Type): Toast {

        val text = """
                家計簿に記入しました。
                ${item.name}：${record.money} 円
            """.trimIndent()

        return Toast.makeText(this, text, Toast.LENGTH_LONG)
    }

    /**
     * エラーダイアログ作成メソッド.
     */
    private fun buildAlert(title:String,message:String) {
        AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK",null)
                .show()
    }

    /**
     * 各数字ボタン押下時の処理.
     * <p>
     * 金額テキストに数字が入力される
     */
    fun input(v: View) {
        // ボタンの数値取得
        val number = (v as Button).text;

        // 金額テキストに取得した数値を入力
        val moneyAmount = findViewById<View>(R.id.moneyAmount) as EditText
        // テキストの1文字目に0が入る事を阻止する
        if (number.equals("0") && moneyAmount.text.length == 0) {
            return
        }
        moneyAmount.text.append(number)
    }

    /**
     * クリアボタン押下時の処理.
     * <p>
     * 金額テキストの入力内容を全て削除する
     */
    fun clear(v: View) {
        val moneyAmount = findViewById<View>(R.id.moneyAmount) as EditText
        moneyAmount.text.clear()
    }

    fun pop(v: View) {

        val money = (findViewById<View>(R.id.moneyAmount) as EditText)

        val length = money.text.length

        if(length == 0) {
            return
        }

        money.text = money.text.replace(length - 1,length,"")
    }

    fun buildArrayAdapter(array : List<String>): ArrayAdapter<String> {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, array)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        return adapter
    }

}
