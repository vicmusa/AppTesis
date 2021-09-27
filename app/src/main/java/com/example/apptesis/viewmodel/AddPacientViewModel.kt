package com.example.apptesis.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apptesis.usercase.RegisterUserCase
import com.example.apptesis.usercase.RetrieveByCIUserCase
import com.example.apptesis.usercase.RetrieveByIDUserCase
import com.example.apptesis.usercase.ValidateExistIDUserCase
import kotlinx.coroutines.launch

class AddPacientViewModel : ViewModel() {

    val isloadig = MutableLiveData<Boolean>()

    val retrieveByCIUserCase = RetrieveByCIUserCase()
    val retrieveByIDUserCase = RetrieveByIDUserCase()
    val validateExistUserCase = ValidateExistIDUserCase()
    val registerUserCase = RegisterUserCase()
    val tipeExiste = MutableLiveData<Int>()
    fun addpaciente(paciente : HashMap<String,String>,ci : String, id : String)
    {
    viewModelScope.launch{

        isloadig.postValue(true)
        val result = retrieveByCIUserCase(ci)
        Log.e("THis",result.ci + result.nombre)
        if(result.nombre.equals("0"))
        {
            val result1 = retrieveByIDUserCase(id)
            Log.e("THis",result1.ci + result1.nombre)
            if(result1.ci.equals("0"))
                {
                 if(validateExistUserCase(id))
                 {
                     registerUserCase(paciente,ci)
                     tipeExiste.postValue(4)
                 }
                 else
                 {
                     tipeExiste.postValue(3)
                 }
                }else
                {
                tipeExiste.postValue(2)
                }

        }
        else
        {
            tipeExiste.postValue(1)
        }
        isloadig.postValue(false)
    }
    }
}