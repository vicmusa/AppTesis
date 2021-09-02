package com.example.apptesis.core

import android.util.Log
import com.google.firebase.database.DataSnapshot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class QueryHistoricalData {

    suspend operator fun invoke(id: String) : DataSnapshot? {


        return withContext(Dispatchers.IO){FirebaseHelper.HelperHistoricos().child(id).get().await()}
    }
}