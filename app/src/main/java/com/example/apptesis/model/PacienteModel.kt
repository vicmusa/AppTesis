package com.example.apptesis.model

data class PacienteModel(var nombre : String,
                         var ci : String,
                         var apellido : String,
                         var id : String,
                         val edad : String,
                         val estatura : String,
                         val sangre : String,
                         val peso : String,
                         val alergias : String,
                         val prepato : String,
                         val fecha : String ) {


    constructor() : this ("0","0","0","0","0","0","0","0","0","0","0",)

    override fun toString(): String {
        var a = nombre+" "+apellido
        return a
    }
}
//  AQUI VAN TODOS LOS VALORES DEL PACIENTE

