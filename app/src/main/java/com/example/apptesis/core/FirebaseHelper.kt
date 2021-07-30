package com.example.apptesis.core

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

object FirebaseHelper {

    fun HelperHistoricos() : DatabaseReference {
        val mdb= FirebaseDatabase.getInstance().getReference("Historicos")
        return mdb
    }
    fun HelperSensores() : DatabaseReference {

        val mdb= FirebaseDatabase.getInstance().getReference("Sensores")

        return mdb
    }
    fun HelperPacientes() : CollectionReference{

        val db = FirebaseFirestore.getInstance().collection("paciente")

        return db
    }
}