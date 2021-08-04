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
                val ci = a.id
                val name = a["nombre"].toString()
                val ap = a["apellido"].toString()
                val id = a["idenUso"].toString()
                paciente = PacienteModel(name, ci, ap, id)
            }
            Log.e("ERROR","ahora aqui")

        }
        return paciente
    }
}