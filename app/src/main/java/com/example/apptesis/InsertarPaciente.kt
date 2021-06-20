package com.example.apptesis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.constraintlayout.motion.widget.TransitionBuilder.validate
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.properties.Delegates

class InsertarPaciente : AppCompatActivity() {
    var fraccion: String = "Eta"
    var instrumento: String = "Lira"
    private lateinit var database: FirebaseFirestore
    private lateinit var  txtName: EditText
    private lateinit var  txtLastName: EditText
    private lateinit var  txtEdad: EditText
    private lateinit var  txtCI : EditText
    private lateinit var  txtEstatura: EditText
    private lateinit var  txtPeso: EditText
    private lateinit var  txtID: EditText
    private lateinit var grupoSanguineo : Spinner
    var name by Delegates.notNull<String>()
    var apellido by Delegates.notNull<String>()
    var edad by Delegates.notNull<String>()
    var CI by Delegates.notNull<String>()
    var estatura  by Delegates.notNull<String>()
    var peso by Delegates.notNull<String>()
    var IDdisp by Delegates.notNull<String>()
    var sange by Delegates.notNull<String>()
    var prevPato by Delegates.notNull<String>()
    var alergias by Delegates.notNull<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertar_paciente)
        inicializar()
        prepareActionBar()
    }

    private fun prepareActionBar() {
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun inicializar() {
        TODO("Not yet implemented, TERMINAR LA IMPLEMENTACION")
        database = FirebaseFirestore.getInstance()
        txtName=findViewById(R.id.etxtnombre)
        txtLastName=findViewById(R.id.etxtapellido)
        txtEdad=findViewById(R.id.etxtedad)
        txtCI=findViewById(R.id.etxtci)
        txtEstatura=findViewById(R.id.etxtestatura)
        txtPeso=findViewById(R.id.etxtpeso)
        txtID=findViewById(R.id.etxtid)

    }

    fun cancelar(view: View) {onBackPressed()}
    fun addPaciente(view: View) {

        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(apellido)
                && !TextUtils.isEmpty(edad) && !TextUtils.isEmpty(peso) && !TextUtils.isEmpty(estatura) && !TextUtils.isEmpty(CI)){
            if(validate())
            {

            }
        }else
        {
            Toast.makeText(applicationContext,R.string.emptyfields, Toast.LENGTH_LONG).show()
        }
    }

    private fun validate() : Boolean {
        TODO("Not yet implemented")
    }


}