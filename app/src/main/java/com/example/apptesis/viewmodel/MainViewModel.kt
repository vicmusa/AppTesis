package com.example.apptesis.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apptesis.model.PacienteModel
import com.example.apptesis.usercase.RetrieveByCIUserCase
import com.example.apptesis.usercase.RetrieveByIDUserCase
import com.example.apptesis.usercase.ValidateUserCase
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    var noExiste = MutableLiveData<Boolean>()
    var paciente = MutableLiveData<PacienteModel>()
    val ValidateUserCase = ValidateUserCase()
    val RetriveByCIUserCase = RetrieveByCIUserCase()
    val RetrieveByIDUserCase = RetrieveByIDUserCase()
    val ask1 = MutableLiveData<Boolean>()
    var a = PacienteModel()

    var isloading = MutableLiveData<Boolean>()
    fun addData(ci: String, id: String) {
        viewModelScope.launch {
            Log.e("ERROR", "ENTRO AQUI")
             // Aparece el Progress()
            var case = ValidateUserCase(ci, id)
            Log.e("ERROR",case.toString())
            when (case) {
                1 -> a= RetriveByCIUserCase(ci)
                2 -> ask()
                3 -> a= RetrieveByIDUserCase(id)
            }
            Log.e("ERROR",a.id.toString())
            when(a.id){

                "null"-> noExiste.postValue(false)
                "0"-> noExiste.postValue(false)
                else-> paciente.postValue(a)
            }

        }
    }

    private fun ask() {

        ask1.postValue(true)

    }
}