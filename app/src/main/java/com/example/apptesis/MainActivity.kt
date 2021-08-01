package com.example.apptesis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.apptesis.databinding.ActivityMainBinding
import com.google.firebase.database.*


class MainActivity : AppCompatActivity() {
    private lateinit var txthr : TextView
    private lateinit var txtspo2 : TextView
    private lateinit var txttemp : TextView
    private lateinit var binding : ActivityMainBinding
    private var isClikeable = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.fabmenu.setOnClickListener{

            if(!isClikeable)
            {
                binding.fabadd.visibility = View.VISIBLE
                binding.fabadd2.visibility = View.VISIBLE
                binding.fabinfo.visibility = View.VISIBLE
                binding.fabtimeline.visibility = View.VISIBLE


            }
            else{

                binding.fabadd.visibility = View.GONE
                binding.fabadd2.visibility = View.GONE
                binding.fabinfo.visibility = View.GONE
                binding.fabtimeline.visibility = View.GONE


            }
            binding.fabadd.isClickable = !isClikeable
            binding.fabadd2.isClickable = !isClikeable
            binding.fabtimeline.isClickable = !isClikeable
            binding.fabinfo.isClickable = !isClikeable
            isClikeable = !isClikeable
        }
    }

    private fun goto(identify : Int){

        when(identify)
        {
            1 -> startActivity(Intent(this,AddPacienteActivity::class.java))
            2 -> startActivity(Intent(this,TimeLineActivity::class.java))
            3 -> startActivity(Intent(this,InfoActivity::class.java))
        }

    }


}