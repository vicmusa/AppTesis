package com.example.apptesis

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.provider.Settings.Global.getString
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
import com.example.apptesis.databinding.ItemInfoBinding
import com.example.apptesis.databinding.ItemPacienteBinding
import com.google.firebase.database.*
import com.example.apptesis.model.PacienteModel
import com.example.apptesis.view.MainActivity
import www.sanju.motiontoast.MotionToast
import java.sql.Date
import java.text.DateFormat


class PacientesAdapter(val listPacientes:MutableList<PacienteModel>,context: Context) :  RecyclerView.Adapter<PacientesAdapter.PacienteHolder>() {

    val c = context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PacienteHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PacienteHolder(layoutInflater.inflate(R.layout.item_paciente, parent, false))

    }
    override fun getItemCount(): Int = listPacientes.size

    override fun onBindViewHolder(holder: PacienteHolder, position: Int) {
        holder.render(listPacientes[position],c)
    }

    fun deleteItem(i : Int)
    {
        Log.e("This", "acatoy")
        notifyItemRemoved(i)
    }
    fun addItem(paciente: PacienteModel,position: Int)
    {
        notifyItemInserted(position)
    }


    class PacienteHolder(val view: View):RecyclerView.ViewHolder(view){

            private val binding = ItemPacienteBinding.bind(view)




        fun render(paciente : PacienteModel,context: Context)
        {

                binding.tvName.text= paciente.nombre +" "+ paciente.apellido
                binding.tvCI.text = "Cédula: "+paciente.ci
                val fdate = DateFormat.getDateInstance()
                binding.tvFecha.text = "Fecha de Inicio: "+ fdate.format(Date(paciente.fecha.toLong()))
                binding.tvEdad.text = "Edad: "+paciente.edad + "años"
                binding.tvPeso.text = "Peso: "+paciente.peso + "Kg"
                binding.tvEstatura.text = "Estatura: "+paciente.estatura + "cm"
                binding.tvPrevPato.text = "Factores de Riesgo: "+paciente.prepato
                binding.tvALERGIAS.text ="Alergias: "+paciente.alergias
                binding.tvID.text ="Dispositivo en uso: "+paciente.id
                FirebaseHelper.SuscribeTopic(paciente.id)
            val listener = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {

                        val flag = snapshot.child("flag").getValue().toString().toInt()
                        val spo2 = snapshot.child("spo2").getValue().toString().toDouble()
                        val hr = snapshot.child("hr").getValue().toString().toDouble()
                        val temp = snapshot.child("temp").getValue().toString().toDouble()
                        val spo2String = String.format("%.1f", spo2)
                        val hrString = String.format("%.1f", hr)
                        val tempString = String.format("%.1f", temp)
                        binding.tvTemp.text = tempString
                        binding.tvHr.text = hrString
                        binding.tvSpo2.text = spo2String

                        var yellow: Boolean
                    if (flag != 0 ||  temp > 38.5 || temp < 33.0 || spo2 <= 94.0 || hr > 100 || hr < 40) {
                            yellow = true
                                binding.allRv.setBackgroundResource(R.drawable.recycler_yellow)
                            if (flag != 0) {
                                binding.tvDedo.visibility = View.VISIBLE
                            } else {
                                binding.tvValor.visibility = View.VISIBLE

                            }

                        } else {
                            yellow = false
                            binding.tvDedo.visibility = View.GONE
                            binding.tvValor.visibility = View.GONE
                            binding.allRv.setBackgroundResource(R.drawable.recycler)

                        }
                        Log.e("YEllOW", yellow.toString())

                    }
                }
                override fun onCancelled(error: DatabaseError) {

                }

            }
            FirebaseHelper.HelperSensores().child(paciente.id).addValueEventListener(listener)
            binding.arrowRecycler.setOnClickListener {
                if(binding.infoTab.visibility==View.GONE)
                {
                    binding.infoTab.visibility=View.VISIBLE
                    binding.arrowRecycler.setMinAndMaxProgress(0.0f,0.5f)
                    binding.arrowRecycler.playAnimation()
                }
                else{
                    binding.infoTab.visibility= View.GONE
                    binding.arrowRecycler.setMinAndMaxProgress(0.5f,1.0f)
                    binding.arrowRecycler.playAnimation()
                }

             }
        }
    }

    }


