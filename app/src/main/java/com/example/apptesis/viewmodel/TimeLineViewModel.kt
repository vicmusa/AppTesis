package com.example.apptesis.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apptesis.usercase.RetrieveDataUserCase
import kotlinx.coroutines.launch

class TimeLineViewModel : ViewModel() {
    val RetrieveDataUserCase = RetrieveDataUserCase()
    fun createSpinner(){
        viewModelScope.launch{
            val result =RetrieveDataUserCase()
        }

    }
}


