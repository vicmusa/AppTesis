package com.example.apptesis.usercase

import com.example.apptesis.core.QueryGetUser
import com.example.apptesis.model.PacienteModel

class RetrieveByCIUserCase {

    suspend operator fun invoke(id : String) : PacienteModel {
            var paciente = PacienteModel()
            val QueryGetUser = QueryGetUser()
            val result = QueryGetUser(id)

        if (result != null) {
            if (result.exists())
            {
                    val ci = result.id // CAMBIARLO A TO OBJECT
                    val nombre = result["nombre"].toString()
                    val ap = result["apellido"].toString()
                    val idDisp = result["idenUso"].toString()
                    val edad = result["edad"].toString()
                    val estatura = result["estatura"].toString()
                    val sangre = result["gruposang"].toString()
                    val peso = result["peso"].toString()
                    val alergias = result["alergias"].toString()
                    val prepato = result["prevpato"].toString()
                    val fecha = result["fecha"].toString()
                paciente = PacienteModel(nombre,ci,ap,idDisp,edad,estatura,sangre,peso, alergias, prepato, fecha)
            return paciente
            }
    }
        return paciente
}
}