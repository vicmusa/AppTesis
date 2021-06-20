package com.example.apptesis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.constraintlayout.motion.widget.TransitionBuilder.validate
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.properties.Delegates

class InsertarPaciente : AppCompatActivity() {
    private lateinit var database: FirebaseFirestore
    private lateinit var txtName: EditText
    private lateinit var txtLastName: EditText
    private lateinit var txtEdad: EditText
    private lateinit var txtCI: EditText
    private lateinit var txtEstatura: EditText
    private lateinit var txtPeso: EditText
    private lateinit var txtID: EditText
    private lateinit var grupoSanguineo: Spinner
    private lateinit var checkUs : CheckBox
    var name by Delegates.notNull<String>()
    var apellido by Delegates.notNull<String>()
    var edad by Delegates.notNull<String>()
    var CI by Delegates.notNull<String>()
    var estatura by Delegates.notNull<String>()
    var peso by Delegates.notNull<String>()
    var IDdisp = " "
    var sange by Delegates.notNull<String>()
    var prevPato = ""
    var alergias = ""
    var tiempo = 0



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
        database = FirebaseFirestore.getInstance()
        txtName = findViewById(R.id.etxtnombre)
        txtLastName = findViewById(R.id.etxtapellido)
        txtEdad = findViewById(R.id.etxtedad)
        txtCI = findViewById(R.id.etxtci)
        txtEstatura = findViewById(R.id.etxtestatura)
        txtPeso = findViewById(R.id.etxtpeso)
        txtID = findViewById(R.id.etxtID)
        grupoSanguineo = findViewById(R.id.gruprosang)
        checkUs= findViewById(R.id.usando)

        ArrayAdapter.createFromResource(
            this,
            R.array.Gruposang,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            grupoSanguineo.adapter = adapter
        }
    }

    fun cancelar(view: View) {
        onBackPressed()
    }

    fun addPaciente(view: View) {

        sange = grupoSanguineo.selectedItem.toString()
        name = txtName.text.toString()
        apellido = txtLastName.text.toString()
        edad = txtEdad.text.toString()
        peso = txtPeso.text.toString()
        estatura = txtEstatura.text.toString()
        CI = txtCI.text.toString()
        if(checkUs.isChecked)
        {
            IDdisp=txtID.text.toString()
        }

        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(apellido)
            && !TextUtils.isEmpty(edad) && !TextUtils.isEmpty(peso) && !TextUtils.isEmpty(estatura) && !TextUtils.isEmpty(
                CI)
        ) {
            if (true) {
                database.collection("pacientes").document(CI).set(
                    hashMapOf(
                        "nombre" to name,
                        "apellido" to apellido,
                        "edad" to edad,
                        "peso" to peso,
                        "estatura" to estatura,
                        "Tipo de Sangre" to grupoSanguineo,
                        "Patologías Previas" to prevPato,
                        "Alergias" to alergias,
                        "IDenUso" to IDdisp,


                        )
                )
                    }
                } else {
            Toast.makeText(applicationContext, R.string.emptyfields, Toast.LENGTH_LONG).show()
                }
    }

    private fun validate(): Boolean {
        return true
    }

    fun usandoID(view: View) {
        if (view is CheckBox) {
            val checked: Boolean = view.isChecked
            when (view.id) {
                R.id.usando -> {
                    if (checked) {
                        txtID.visibility = View.VISIBLE
                    } else {
                        txtID.visibility = View.INVISIBLE
                    }
                }
            }
        }


    }
}