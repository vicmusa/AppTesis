package com.example.apptesis.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apptesis.model.PacienteModel
import com.example.apptesis.usercase.*
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    var dealta = MutableLiveData<Boolean>()
    var isLoading = MutableLiveData<Boolean>()
    var noExiste = MutableLiveData<Boolean>()
    var paciente = MutableLiveData<PacienteModel>()
    val ValidateUserCase = ValidateUserCase()
    val ChangeIDUserCase = ChangeIdUserCase()
    var idNoExiste = MutableLiveData<Boolean>()
    var asignado = MutableLiveData<PacienteModel>()
    val RetriveByCIUserCase = RetrieveByCIUserCase()
    val RetrieveByIDUserCase = RetrieveByIDUserCase()
    val ValidateExistIDUserCase = ValidateExistIDUserCase()
    val change = MutableLiveData<Boolean>()
    var a = PacienteModel()

    var isloading = MutableLiveData<Boolean>()
    fun addData(ci: String, id: String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            Log.e("ERROR", "ENTRO AQUI")
             // Aparece el Progress()
            var case = ValidateUserCase(ci, id)
            Log.e("ERROR",case.toString())
            when (case) {
                1 -> a= RetriveByCIUserCase(ci)
                2 -> a= ask(ci, id)
                3 -> a= RetrieveByIDUserCase(id)
            }
            Log.e("ERROR",a.id.toString())
            isLoading.postValue(false)
            when(a.id){

                "null"-> noExiste.postValue(false)
                "0"-> noExiste.postValue(false)
                "x"-> asignado.postValue(a)
                "?"-> idNoExiste.postValue(false)
                else-> paciente.postValue(a)
            }

        }
    }

    suspend fun ask(ci: String, id: String): PacienteModel {

        var paciente = PacienteModel()
        val result = RetriveByCIUserCase(ci)
        if(result.id == "null")
        {
            paciente = result
        }
        else {
            val result1 = RetrieveByIDUserCase(id)
            if (result1.id != "0") {
                paciente = result1
                Log.e("ERROR", result1.ci)
                paciente.id = "x"
            }
            else{
                if(ValidateExistIDUserCase(id))
                {
                    Log.e("ERROR", "ESTOY AQUI")
                    ChangeIDUserCase(ci,id)
                    val result3 = RetriveByCIUserCase(ci)
                    paciente = result3
                    change.postValue(true)

                }
                else
                {
                    paciente.id="?"
                }
            }
        }

        return paciente
    }
    fun alta (ci: String){
                viewModelScope.launch {
            val deleteUserCase = DeleteUserCase()
            deleteUserCase(ci)
            dealta.postValue(true)
        }
    }
}