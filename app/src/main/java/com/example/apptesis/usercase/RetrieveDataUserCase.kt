package com.example.apptesis.usercase

import android.R
import android.widget.ArrayAdapter
import com.example.apptesis.core.FirebaseHelper
import com.example.apptesis.model.PacienteModel
import com.google.firebase.ktx.Firebase

class RetrieveDataUserCase {

    suspend operator fun invoke():List<PacienteModel>{
        val listPaciente = mutableListOf<PacienteModel>()
        FirebaseHelper.HelperPacientes()
            .whereNotEqualTo("idenUso", "ninguna")
            .get()
            .addOnSuccessListener { resultado ->
                for (documento in resultado) {
                    // Obtengo todos los documentos que tienen ID y los inserto en una lista
                    val ci = documento.id
                    val name = documento["nombre"].toString()
                    val ap = documento["apellido"].toString()
                    val id = documento["idenUso"].toString()
                    val paciente = PacienteModel(name, ci, ap, id)
                    listPaciente.add(paciente)

                }
            }
        return if(listPaciente.isNullOrEmpty())
            emptyList()
        else listPaciente
    }
}