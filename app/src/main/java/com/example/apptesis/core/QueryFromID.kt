package com.example.apptesis.core

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class QueryFromID {

    suspend operator fun invoke(id : String): DataSnapshot? {

        return withContext(Dispatchers.IO){
            FirebaseHelper.HelperSensores().child(id).get().await()
        }
    }
}