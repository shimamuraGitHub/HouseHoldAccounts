package com.exsample.householdaccounts.controller.activity.main

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.exsample.householdaccounts.R
import com.exsample.householdaccounts.controller.activity.find
import com.exsample.householdaccounts.controller.activity.list.ListActivity
import com.exsample.householdaccounts.controller.widgets.*
import com.exsample.householdaccounts.db.DBOpenHelper
import kotlinx.android.synthetic.main.activity_config.*
import kotlinx.android.synthetic.main.app_bar_config.*

class MainActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener {

    val dbHelper = DBOpenHelper(context = this,version = 1)
    lateinit var service: MainService

    lateinit var typeSpinner:Spinner
    lateinit var moneyEdit: EditText

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper.onCreate(dbHelper.writableDatabase)
        service = MainService(dbHelper)

        typeSpinner = find<Spinner>(R.id.itemNames)
        typeSpinner.buildRecordTypeAdapter(service.findRecordTypes())

        moneyEdit = find<EditText>(R.id.moneyAmount)
        setNav()
    }

    private fun setNav(){
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    /**
     * 登録ボタン押下時の処理.
     *
     */
    fun register(v: View){

        val message = service.registerRecord(moneyEdit,typeSpinner)

        if(message.success){
            moneyEdit.clear()
            Toast.makeText(this, message.message, Toast.LENGTH_LONG).show()
            return
        }

        val alertDialog = DialogBuilder(this)
        alertDialog.buildMessage(message)
        alertDialog.show()
    }

    /**
     * 各数字ボタン押下時の処理.
     * <p>
     * 金額テキストに数字が入力される
     */
    fun input(v: View) {

        val numberButton = v as Button

        // テキストの1文字目に0が入る事を阻止する
        if (numberButton.isZero() && moneyEdit.isBlank()) {
            return
        }

        moneyEdit.append(numberButton.text)
    }

    /**
     * ◀×ボタン押下処理.
     *
     */
    fun pop(v: View) {

        if(moneyEdit.isBlank()) {
            return
        }
        moneyEdit.pop()
    }

    /**
     * クリアボタン押下処理.
     */
    fun clear(v:View) = moneyEdit.clear()

    fun toList(v:View?){
        val intent = Intent(this, ListActivity::class.java)
        startActivityForResult(intent,1)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.config, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_main -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivityForResult(intent,1)
            }
            R.id.nav_config -> {
                val intent = Intent(this, ListActivity::class.java)
                startActivityForResult(intent,1)
            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
