package com.example.apptesis.core

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class QueryData {

    suspend operator fun invoke() : QuerySnapshot? {

        return withContext(Dispatchers.IO){FirebaseHelper.HelperPacientes().whereNotEqualTo("idenUso", "ninguna")
            .get().await() // CAMBIAR POR BUSCAR EN ROOM
        }
    }
}