package com.exsample.householdaccounts.controller.activity.navigation

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.exsample.householdaccounts.R
import com.exsample.householdaccounts.controller.activity.config.ConfigActivity
import com.exsample.householdaccounts.controller.activity.list.ListActivity
import com.exsample.householdaccounts.controller.activity.main.MainActivity
import kotlinx.android.synthetic.main.activity_config.*
import kotlinx.android.synthetic.main.app_bar_config.*

/**
 * Created by ryosuke on 2018/02/18.
 */
abstract class NavigationListener :  AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    protected fun setNavigation(){
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
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
        var intent: Intent? = null
        when (item.itemId) {
            R.id.nav_main -> {
                intent = Intent(this, MainActivity::class.java)
            }
            R.id.nav_config -> {
                intent = Intent(this, ConfigActivity::class.java)
            }
            R.id.nav_list -> {
                intent = Intent(this, ListActivity::class.java)
            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }
        if(intent != null){
            startActivityForResult(intent,1)
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}