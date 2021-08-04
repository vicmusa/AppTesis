package com.example.apptesis.usercase

import android.R
import android.util.Log
import android.widget.ArrayAdapter
import com.example.apptesis.core.FirebaseHelper
import com.example.apptesis.core.QueryData
import com.example.apptesis.model.PacienteModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RetrieveDataUserCase {

    suspend operator fun invoke():List<PacienteModel>{
        // AQUI CAMBIARA A ROOM!
        val QueryData = QueryData()
        val result = QueryData()
        val listPaciente = mutableListOf<PacienteModel>()

        if (result != null) for(documento in result) {
            // Obtengo todos los documentos que tienen ID y los inserto en una lista
            val ci = documento.id
            val name = documento["nombre"].toString()
            val ap = documento["apellido"].toString()
            val id = documento["idenUso"].toString()
            val paciente = PacienteModel(name, ci, ap, id)
            listPaciente.add(paciente)

        }
            return listPaciente

        }
    }
