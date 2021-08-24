package com.example.apptesis.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.apptesis.R
import com.example.apptesis.databinding.ActivityAddPacientBinding

class AddPacientActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddPacientBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAddPacientBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.nbEstatura.maxValue = 250
        binding.nbEstatura.minValue = 120
        binding.npEdad.maxValue = 110
        binding.npEdad.minValue = 10
        binding.npPeso.maxValue = 250
        binding.npPeso.minValue = 30

        ArrayAdapter.createFromResource(this, R.array.Gruposang,android.R.layout.simple_spinner_item).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinner2.adapter = adapter }

        binding.btnAddPato.setOnClickListener {
            if(binding.tvPrevpato.equals("Ninguna"))
            {
                binding.tvPrevpato.text = binding.spinner3.selectedItem.toString()
            }
            else
            {
                binding.tvPrevpato.text = binding.tvPrevpato.text.toString() + "/" + binding.spinner3.selectedItem.toString()
            }
        }
        binding.btnAddAler.setOnClickListener {
            if(binding.tvAlergias.equals("Ninguna"))
            {
                binding.tvAlergias.text = binding.spinner4.selectedItem.toString()
            }
            else
            {
                binding.tvAlergias.text = binding.tvAlergias.text.toString() + "/" + binding.spinner4.selectedItem.toString()
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
            val apellido = binding.etApellido.text.toString()
            val cedula = binding.etCI.text.toString()
            val id = binding.etID.text.toString()
            val edad = binding.npEdad.value.toString()
            val sangre = binding.spinner2.selectedItem.toString()
            val estatura = binding.nbEstatura.value.toString()
            val peso = binding.npPeso.value.toString()
            val prevpato = binding.tvPrevpato.text.toString()
            val alergias = binding.tvAlergias.text.toString()
            val date = System.currentTimeMillis()

            if(name.isNotBlank() && apellido.isNotBlank() && cedula.isNotBlank() && id.isNotBlank())
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
                    "idenUso" to id,
                    "fecha" to date)


            }
            else{
                Toast.makeText(this, "Los Campos no pueden estar vacios",Toast.LENGTH_LONG).show()
            }

        }
    }
}