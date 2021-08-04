package com.example.apptesis.usercase

class ValidateUserCase  {


    suspend operator fun invoke(ci : String, id: String) : Int {

        if(ci.isNotBlank() && id.isBlank())
        {
            return 1
        }
        if(ci.isNotBlank() && id.isNotBlank())
        {
            return 2
        }
        if(ci.isBlank() && id.isNotBlank())
        {
            return 3
        }

            return 0
    }

}