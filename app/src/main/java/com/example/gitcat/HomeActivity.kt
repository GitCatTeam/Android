package com.example.gitcat

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //초기화
        loadFragment(HomeFragment())
        navigationView.selectedItemId = R.id.nav_home

        navigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_check->{
                    loadFragment(CheckFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_home->{
                    loadFragment(HomeFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_report->{
                    loadFragment(ReportFragment())
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }

    private fun loadFragment(fragment: Fragment) {
        // load fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
