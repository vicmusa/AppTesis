package com.example.apptesis.usercase

import com.example.apptesis.core.FirebaseHelper

class RegisterUserCase {

    suspend operator fun invoke(paciente : HashMap<String, String>, ci : String)
    {
        FirebaseHelper.HelperPacientes().document(ci).set(paciente)
    }

}