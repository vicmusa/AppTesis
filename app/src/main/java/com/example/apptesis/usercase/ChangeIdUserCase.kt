package com.example.apptesis.usercase

import com.example.apptesis.core.FirebaseHelper
import kotlinx.coroutines.tasks.await

class ChangeIdUserCase {

    suspend operator fun invoke(ci: String, id: String){


        FirebaseHelper.HelperPacientes().document(ci).update("idenUso",id).await()

    }
}