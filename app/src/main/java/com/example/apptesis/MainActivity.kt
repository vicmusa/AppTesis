package com.example.apptesis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.apptesis.fragments.DataFragment
import com.example.apptesis.fragments.HomeFragment
import com.example.apptesis.fragments.InfoFragment
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFragment = HomeFragment()
        val infoFragment = InfoFragment()
        val dataFragment = DataFragment()

        changeFragment(homeFragment)
        val nav_menu : BottomNavigationView = findViewById(R.id.menu_nav)
        nav_menu.setOnNavigationItemSelectedListener { when(it.itemId){
            R.id.home -> changeFragment(homeFragment)
            R.id.evo -> changeFragment(dataFragment)
            R.id.info -> changeFragment(infoFragment)

        }
            true
        }
    }

    private fun changeFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper,fragment)
            commit()
        }


}