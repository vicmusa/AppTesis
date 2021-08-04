package com.example.apptesis.core

import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class QueryGetUser {

    suspend operator fun invoke(id : String) : DocumentSnapshot? {

        return withContext(Dispatchers.IO){FirebaseHelper.HelperPacientes().document(id).get().await()}
    }
}