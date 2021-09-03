package com.example.apptesis.usercase

import android.app.DownloadManager
import android.util.Log
import com.example.apptesis.core.QueryGetUserByID
import com.example.apptesis.model.PacienteModel

class RetrieveByIDUserCase {

    suspend operator fun invoke(id: String):PacienteModel {
        val QueryGetUserByID = QueryGetUserByID()
        val result = QueryGetUserByID(id)
        var paciente = PacienteModel()
        Log.e("ERROR", result.toString())
        Log.e("ERROR","PUDE ENTRAR AQUI 2")
        if (result != null) {
            Log.e("ERROR", "ESTOY ANTES DEL a")
            if(!result.documents.isEmpty())
            {
                val a=result.documents[0]
                val ci = a.id // CAMBIARLO A TO OBJECT
                val nombre = a["nombre"].toString()
                val ap = a["apellido"].toString()
                val idDisp = a["idenUso"].toString()
                val edad = a["edad"].toString()
                val estatura = a["estatura"].toString()
                val sangre = a["gruposang"].toString()
                val peso = a["peso"].toString()
                val alergias = a["alergias"].toString()
                val prepato = a["prevpato"].toString()
                val fecha = a["fecha"].toString()
                paciente = PacienteModel(nombre,ci,ap,idDisp,edad,estatura,sangre,peso, alergias, prepato, fecha)
            }
            Log.e("ERROR","ahora aqui")

        }
        return paciente
    }
}