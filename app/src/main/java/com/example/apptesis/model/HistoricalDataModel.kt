package com.example.apptesis.model

data class HistoricalDataModel(val ts: Long, val spo2 : Float, val hr: Float, val temp : Float) {

    override fun toString(): String {
        return ts.toString()+" "+spo2+" "+hr+" "+temp
    }
}