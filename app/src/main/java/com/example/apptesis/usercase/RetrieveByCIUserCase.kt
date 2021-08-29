package com.example.apptesis.usercase

import com.example.apptesis.core.QueryGetUser
import com.example.apptesis.model.PacienteModel

class RetrieveByCIUserCase {

    suspend operator fun invoke(id : String) : PacienteModel {

            val QueryGetUser = QueryGetUser()
            val result = QueryGetUser(id)

        if (result != null) {
                    val ci = result.id // CAMBIARLO A TO OBJECT
                    val nombre = result["nombre"].toString()
                    val ap = result["apellido"].toString()
                    val idDisp = result["idenUso"].toString()
                    val paciente = PacienteModel(nombre,ci,ap,idDisp)
            return paciente
                     }
        else{
            return PacienteModel()
        }
    }
}