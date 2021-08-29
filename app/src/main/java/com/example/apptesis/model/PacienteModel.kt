package com.example.apptesis.model

import com.google.gson.annotations.SerializedName

data class PacienteModel(@SerializedName("nombre") var nombre : String, @SerializedName("id") var ci : String, @SerializedName("apellido") var apellido : String, @SerializedName("idenUSo") var id : String) {


    constructor() : this ("0","0","0","0")
    override fun toString(): String {
        var a = ci+":"+nombre+" "+apellido
        return a
    }
}
//  AQUI VAN TODOS LOS VALORES DEL PACIENTE

