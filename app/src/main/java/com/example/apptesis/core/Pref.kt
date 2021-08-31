package com.example.apptesis.core

import android.content.Context
import android.util.Log
import com.example.apptesis.model.PacienteModel

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Pref (val context: Context) {

    val Name = "Mydb"
    val SHARED_KEY = "MyList"
    val storage = context.getSharedPreferences(Name, 0)

    fun save (listpacientes : List<PacienteModel>){
        val gson = Gson()
        val json : String = gson.toJson(listpacientes)
        storage.edit().putString(SHARED_KEY,json).apply()

    }
    fun getList() : List<PacienteModel> {
        val json = storage.getString(SHARED_KEY, null)
        if(json.isNullOrEmpty())
        {
            return emptyList()
        }
        var type = object : TypeToken<PacienteModel>() {}.type
        Log.e("This",json)
        val list = Gson().fromJson(json,Array<PacienteModel>::class.java).asList()
        Log.e("This",list.toString())
        return list
    }



}