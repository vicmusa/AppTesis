package com.example.apptesis

data class Paciente(var nombre : String, var ci : String, var apellido : String, var id : String) {

    override fun toString(): String {
        var a = ci+":"+nombre+" "+apellido
        return a
    }
}
//  AQUI VAN TODOS LOS VALORES DEL PACIENTE

