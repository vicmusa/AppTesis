package com.example.apptesis.model

import com.example.apptesis.R

class Repository {

    companion object{
        val diarrea = InnerInfoModel("Diearrea", R.raw.diarrea,"LA DIERRA ES UNA CAGALADERA DURISIMA")

        val listrecom = listOf<InnerInfoModel>(diarrea)
        val recomendaciones = InfoModel("Recomendaciones", listrecom)
        val sintomas = InfoModel("Sintomas",listrecom)



    }
}