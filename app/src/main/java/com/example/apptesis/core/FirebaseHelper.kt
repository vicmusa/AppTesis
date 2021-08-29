package com.example.apptesis.core

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

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

        val db = Firebase.firestore.collection("pacientes")

        return db
    }
}