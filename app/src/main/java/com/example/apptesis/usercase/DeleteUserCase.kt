package com.example.apptesis.usercase

import com.example.apptesis.core.FirebaseHelper

class DeleteUserCase {

    suspend operator fun invoke(ci : String)

    {
        FirebaseHelper.HelperPacientes().document(ci).delete()
    }
}