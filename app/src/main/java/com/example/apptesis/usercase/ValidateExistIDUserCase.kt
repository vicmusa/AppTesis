package com.example.apptesis.usercase

import android.util.Log
import com.example.apptesis.core.QueryFromID

class ValidateExistIDUserCase {

    suspend operator fun invoke(id : String) : Boolean{
        var a = false

        val QueryFromId = QueryFromID()

        val result = QueryFromId(id)

        if (result != null) {
             a = result.exists()
            Log.e("ERROR",result.exists().toString())
        }
            return a
    }
}