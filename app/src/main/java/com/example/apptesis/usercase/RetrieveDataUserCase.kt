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
        // AQUI CAMBIARA A ROOM! O SHARED PREFERENCES
        val QueryData = QueryData()
        val result = QueryData()
        val listPaciente = mutableListOf<PacienteModel>()

        if (result != null) for(documento in result) {
            // Obtengo todos los documentos que tienen ID y los inserto en una lista
            val ci = documento.id // CAMBIARLO A TO OBJECT
            val nombre = documento["nombre"].toString()
            val ap = documento["apellido"].toString()
            val idDisp = documento["idenUso"].toString()
            val edad = documento["edad"].toString()
            val estatura = documento["estatura"].toString()
            val sangre = documento["gruposang"].toString()
            val peso = documento["peso"].toString()
            val alergias = documento["alergias"].toString()
            val prepato = documento["prevpato"].toString()
            val fecha = documento["fecha"].toString()
            val paciente = PacienteModel(nombre,ci,ap,idDisp,edad,estatura,sangre,peso, alergias, prepato, fecha)
            listPaciente.add(paciente)

        }
            return listPaciente

        }
    }
