package com.example.apptesis.usercase

import android.R
import android.util.Log
import android.widget.ArrayAdapter
import com.example.apptesis.core.FirebaseHelper
import com.example.apptesis.core.Pref
import com.example.apptesis.core.QueryData
import com.example.apptesis.model.PacienteModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RetrieveDataUserCase {

    suspend operator fun invoke(pref : Pref):List<PacienteModel>{
        var listPaciente: MutableList<PacienteModel>
        listPaciente=pref.getList().toMutableList()
        return listPaciente }

    }
