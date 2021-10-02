package com.example.apptesis.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.res.ResourcesCompat
import com.example.apptesis.R
import com.example.apptesis.databinding.ActivityAddPacientBinding
import com.example.apptesis.viewmodel.AddPacientViewModel
import www.sanju.motiontoast.MotionToast

class AddPacientActivity : AppCompatActivity() {
    private val font = null
    private lateinit var binding : ActivityAddPacientBinding
    private val vM : AddPacientViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAddPacientBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        prepareActionBar()
        title = "Agregar Paciente"
        binding.nbEstatura.maxValue = 250
        binding.nbEstatura.minValue = 120
        binding.npEdad.maxValue = 110
        binding.npEdad.minValue = 10
        binding.npPeso.maxValue = 250
        binding.npPeso.minValue = 30

        vM.isloadig.observe(this,{
            if(it){
                binding.isloading1.visibility = View.VISIBLE
            }
            else{
                binding.isloading1.visibility = View.GONE
            }
        })
        vM.tipeExiste.observe(this,{
            var a = ""
            when(it)
            {
                1 -> a = "El paciente ya existe"
                2 -> a = "El ID ya esta asignado a otro paciente"
                3 -> a = "El ID no existe"
                4 -> a = "El paciente fue registrado satisfactoriamente"
            }
            if(it!=4) {
                MotionToast.createColorToast(this,getString(R.string.advertencia),a,MotionToast.TOAST_WARNING,MotionToast.GRAVITY_BOTTOM,MotionToast.LONG_DURATION,font)
            }else
            {
                MotionToast.createColorToast(this,
                    getString(R.string.exito),a,MotionToast.TOAST_SUCCESS,MotionToast.GRAVITY_BOTTOM,MotionToast.LONG_DURATION,font )
            }
        })
        ArrayAdapter.createFromResource(this, R.array.PrevPato,android.R.layout.simple_spinner_item).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinner3.adapter = adapter }
        ArrayAdapter.createFromResource(this, R.array.Gruposang,android.R.layout.simple_spinner_item).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinner2.adapter = adapter }
        ArrayAdapter.createFromResource(this,R.array.alergias,android.R.layout.simple_spinner_item).also { adapter ->
            binding.spinner4.adapter = adapter }

        binding.btnAddPato.setOnClickListener {
            if(binding.tvPrevpato.text.toString().equals("Ninguna"))
            {
                binding.tvPrevpato.text = binding.spinner3.selectedItem.toString()
            }
            else
            {
                if(binding.tvPrevpato.text.toString().contains(binding.spinner3.selectedItem.toString()))
                {
                    MotionToast.createColorToast(this,getString(R.string.advertencia),"Ya la patología seleccionada está incluida en la lista",MotionToast.TOAST_WARNING,MotionToast.GRAVITY_BOTTOM,MotionToast.LONG_DURATION,font)
                }
                else{
                binding.tvPrevpato.text = binding.tvPrevpato.text.toString() + "/" + binding.spinner3.selectedItem.toString()
            }
            }
        }
        binding.btnAddAler.setOnClickListener {
            if(binding.tvAlergias.text.toString().equals("Ninguna"))
            {
                binding.tvAlergias.text = binding.spinner4.selectedItem.toString()
            }
            else
            {

                if(binding.tvAlergias.text.toString().contains(binding.spinner4.selectedItem.toString()))
                {
                    MotionToast.createColorToast(this,getString(R.string.advertencia),"Ya la alergia seleccionada está incluida en la lista",MotionToast.TOAST_WARNING,MotionToast.GRAVITY_BOTTOM,MotionToast.LONG_DURATION,font)
                }
                else{
                    binding.tvAlergias.text = binding.tvAlergias.text.toString() + "/" + binding.spinner4.selectedItem.toString()
                }
            }
        }
        binding.btnCanPato.setOnClickListener {
            binding.tvPrevpato.text = "Ninguna"
        }
        binding.btnCanAler.setOnClickListener {
            binding.tvAlergias.text = "Ninguna"
        }
        binding.btnAddPatient.setOnClickListener {

            val name = binding.etNombre.text.toString()
            Log.e("ERROR", name)
            val apellido = binding.etApellido.text.toString()
            val cedula = binding.etCI.text.toString()
            val id = binding.etID.text.toString()
            val edad = binding.npEdad.value.toString()
            val sangre = binding.spinner2.selectedItem.toString()
            val estatura = binding.nbEstatura.value.toString()
            val peso = binding.npPeso.value.toString()
            val prevpato = binding.tvPrevpato.text.toString()
            val alergias = binding.tvAlergias.text.toString()
            val date = System.currentTimeMillis().toString()

            if(name.isNotEmpty() && name.isNotBlank() && apellido.isNotEmpty() && apellido.isNotBlank() && cedula.isNotEmpty() && cedula.isNotBlank() && id.isNotEmpty() && id.isNotBlank())
            {
                Log.e("ERROR", "AQUI")
                val idcaps=id.toUpperCase()
                if(validar(name,apellido,idcaps))
                {
                val paciente= hashMapOf(
                    "nombre" to name,
                    "apellido" to apellido,
                    "edad" to edad,
                    "peso" to peso,
                    "estatura" to estatura,
                    "gruposang" to sangre,
                    "prevpato" to prevpato,
                    "alergias" to alergias,
                    "idenUso" to idcaps,
                    "fecha" to date)

                vM.addpaciente(paciente,cedula,idcaps)
                }


            }
            else{
                MotionToast.createColorToast(this,MotionToast.TOAST_ERROR,getString(R.string.error),getString(R.string.emptyfields),MotionToast.GRAVITY_BOTTOM,MotionToast.LONG_DURATION,font)
            }

        }
    }

    private fun validar(name: String, apellido: String, id: String): Boolean {

        var flag =true
        val patternID = Regex("[A-F0-9]{6}")
        val patternNomAp= Regex("^[A-Za-záéíóúñÁÉÍÓÚ]{2,}")

        if(!patternID.matches(id))
        {
            flag=false
            MotionToast.createColorToast(this,getString(R.string.error),"El id del dispositivo es invalido",MotionToast.TOAST_ERROR,MotionToast.GRAVITY_BOTTOM,MotionToast.LONG_DURATION,null)
        }
        if(!patternNomAp.matches(name) || !patternNomAp.matches(apellido))
        {
            MotionToast.createColorToast(this,getString(R.string.error),"Nombre o apellido invalido",MotionToast.TOAST_ERROR,MotionToast.GRAVITY_BOTTOM,MotionToast.LONG_DURATION,null)
            flag=false
        }
        return flag
    }
    private fun prepareActionBar() {
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}