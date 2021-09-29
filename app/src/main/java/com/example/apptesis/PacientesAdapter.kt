package com.example.apptesis

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.apptesis.core.FirebaseHelper
import com.google.firebase.database.*
import com.example.apptesis.model.PacienteModel
import com.example.apptesis.view.MainActivity
import java.sql.Date
import java.text.DateFormat


class PacientesAdapter(val listPacientes:MutableList<PacienteModel>) :  RecyclerView.Adapter<PacientesAdapter.PacienteHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PacienteHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PacienteHolder(layoutInflater.inflate(R.layout.item_paciente, parent, false))

    }
    override fun getItemCount(): Int = listPacientes.size

    override fun onBindViewHolder(holder: PacienteHolder, position: Int) {
        holder.render(listPacientes[position])
    }

    fun deleteItem(i : Int)
    {
        Log.e("This", "acatoy")
        notifyItemRemoved(i)
    }
    fun addItem(paciente: PacienteModel)
    {
        notifyItemInserted(listPacientes.size)
    }


    class PacienteHolder(val view: View):RecyclerView.ViewHolder(view){

            val arrow = view.findViewById(R.id.arrowRecycler) as LottieAnimationView
            val tvName = view.findViewById(R.id.tvName) as TextView
            val tvTemp = view.findViewById(R.id.tvTemp) as TextView
            val tvHr = view.findViewById(R.id.tvHr) as TextView
            val tvSpo2 = view.findViewById(R.id.tvSpo2) as TextView
            val infoTab = view.findViewById(R.id.infoTab) as LinearLayout
            val tvCI = view.findViewById(R.id.tvCI) as TextView
            val tvFecha = view.findViewById(R.id.tvFecha) as TextView
            val tvEdad = view.findViewById(R.id.tvEdad) as TextView
            val tvPeso = view.findViewById(R.id.tvPeso) as TextView
            val tvEstatura = view.findViewById(R.id.tvEstatura) as TextView
            val tvPrevpato = view.findViewById(R.id.tvPrevPato) as TextView
            val tvAlergias = view.findViewById(R.id.tvALERGIAS) as TextView
            val tvID = view.findViewById(R.id.tvID) as TextView


        fun render(paciente : PacienteModel)
        {
                tvName.text= paciente.nombre +" "+ paciente.apellido
                tvCI.text = "Cédula: "+paciente.ci
                val fdate = DateFormat.getDateInstance()
                tvFecha.text = "Fecha de Inicio: "+ fdate.format(Date(paciente.fecha.toLong()))
                tvEdad.text = "Edad: "+paciente.edad + "años"
                tvPeso.text = "Peso: "+paciente.peso + "Kg"
                tvEstatura.text = "Estatura: "+paciente.estatura + "cm"
                tvPrevpato.text = "Factores de Riesgo: "+paciente.prepato
                tvAlergias.text ="Alergias: "+paciente.alergias
                tvID.text ="Dispositivo en uso: "+paciente.id
            val listener = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot)
                {
                    if(snapshot.exists()) {

                        val spo2 = snapshot.child("spo2").getValue().toString()
                        val hr = snapshot.child("hr").getValue().toString()
                        val temp = snapshot.child("temp").getValue().toString()
                        tvTemp.text = temp
                        tvHr.text = hr
                        tvSpo2.text = spo2

                    }
                }
                override fun onCancelled(error: DatabaseError) {

                }

            }
            FirebaseHelper.HelperSensores().child(paciente.id).addValueEventListener(listener)
            arrow.setOnClickListener {
                if(infoTab.visibility==View.GONE)
                {
                    infoTab.visibility=View.VISIBLE
                    arrow.setMinAndMaxProgress(0.0f,0.5f)
                    arrow.playAnimation()
                }
                else{
                    infoTab.visibility= View.GONE
                    arrow.setMinAndMaxProgress(0.5f,1.0f)
                    arrow.playAnimation()
                }

             }
        }
    }

    }
