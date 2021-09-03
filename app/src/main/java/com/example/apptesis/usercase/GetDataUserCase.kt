package com.example.apptesis.usercase

import android.util.Log
import android.widget.Toast
import com.example.apptesis.core.FirebaseHelper
import com.example.apptesis.core.QueryHistoricalData
import com.example.apptesis.model.HistoricalDataModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.tasks.await

class GetDataUserCase {

    suspend operator fun invoke(id: String, fecha : String): MutableList<HistoricalDataModel> {
        val QueryHistoricalData = QueryHistoricalData()
            var list = mutableListOf<HistoricalDataModel>()
             val result = QueryHistoricalData(id)
        if (result != null) {
            for(ds in result.children) {
                val ts = ds.key.toString().toLong()
                if(ts >= fecha.toLong()/1000) {
                    Log.e("LISTA", "AQUI" + ts.toString())
                    val spo2 = ds.child("spo2").value.toString().toFloat()
                    val hr: Float = ds.child("hr").value.toString().toFloat()
                    val temp = ds.child("temp").value.toString().toFloat()
                    list.add(HistoricalDataModel(ts, spo2, hr, temp))
                    Log.e("LISTA", list.toString())
                }
            }
        }
        Log.e("LISTA", list.toString() +" VICTO EPA")
        return list
    }

    }
