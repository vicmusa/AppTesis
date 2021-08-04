package com.example.apptesis.core

import android.util.Log
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class QueryGetUserByID {

    suspend operator fun invoke(id: String): QuerySnapshot? {

        return withContext(Dispatchers.IO) {
            Log.e("ERROR","PUDE ENTRAR AQUI")
            FirebaseHelper.HelperPacientes().whereEqualTo("idenUso", id).limit(1).get().await()
        }
    }
}