package com.example.apptesis.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apptesis.core.Pref
import com.example.apptesis.model.PacienteModel
import com.example.apptesis.usercase.GetDataUserCase
import com.example.apptesis.usercase.GraphUserCase
import com.example.apptesis.usercase.RetrieveDataUserCase
import com.github.mikephil.charting.data.LineData
import kotlinx.coroutines.launch

class TimeLineViewModel : ViewModel() {
    var listToAdapter = MutableLiveData<List<PacienteModel>>()
    var listTimeStamp = MutableLiveData<List<Long>>()
    var isLoading = MutableLiveData<Boolean>()
    var dataGraph = MutableLiveData<LineData>()
    val RetrieveDataUserCase = RetrieveDataUserCase()
    val GetDataUserCase = GetDataUserCase()
    val GraphUserCase = GraphUserCase()

    fun createSpinner(pref: Pref){
        viewModelScope.launch{
            val result =RetrieveDataUserCase(pref)
            listToAdapter.postValue(result)
        }
    }
    fun graph(id : String,fecha : String, context: Context){
        Log.e("LISTA","ENTRE AQUI")
        viewModelScope.launch {
            Log.e("LISTA", "ESTOY EN LA CORRUTINA")
            isLoading.postValue(true)
            val result = GetDataUserCase(id,fecha)
            Log.e("LISTA", result.toString())
            val ts = mutableListOf<Long>()
            for(it in result)
            {
                ts.add(it.ts)
            }
            Log.e("LISTA", ts.toString())
            val result2 = GraphUserCase(result,context)
            listTimeStamp.postValue(ts)
            dataGraph.postValue(result2)
            isLoading.postValue(false)


        }
    }
}


