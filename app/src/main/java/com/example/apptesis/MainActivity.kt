package com.example.apptesis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.*


class MainActivity : AppCompatActivity() {
    private lateinit var txthr : TextView
    private lateinit var txtspo2 : TextView
    private lateinit var txttemp : TextView
    private lateinit var mdb: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startActivity(Intent(this,TimeLineActivity::class.java))
        // changeFragment(homeFragment)
        inicializar()
        verDatos()
        /*val nav_menu : BottomNavigationView = findViewById(R.id.menu_nav)
        nav_menu.setOnNavigationItemSelectedListener { when(it.itemId){
            R.id.home -> changeFragment(homeFragment)
            R.id.evo -> changeFragment(dataFragment)
            R.id.info -> changeFragment(infoFragment)

        }
            true
        }*/

    }

    private fun verDatos() {
        val listener = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot)
            {
                if(snapshot.exists()) {
                    val spo2 = snapshot.child("spo2").getValue().toString()
                    val hr = snapshot.child("hr").getValue().toString()
                    val temp = snapshot.child("temp").getValue().toString()

                    txtspo2.setText(spo2)
                    txthr.setText(hr)
                    txttemp.setText(temp)
                }
            }
            override fun onCancelled(error: DatabaseError) {

            }

        }
        mdb.child("ASD123").addValueEventListener(listener)
    }

    private fun inicializar() {
        txthr=findViewById(R.id.hr)
        txttemp=findViewById(R.id.temp)
        txtspo2=findViewById(R.id.spo2)
        mdb=FirebaseDatabase.getInstance().getReference("Sensores")
    }


   /* private fun changeFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper,fragment)
            commit()
        }*/


}