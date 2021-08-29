package com.example.apptesis

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.apptesis.core.FirebaseHelper
import com.google.firebase.database.*
import com.example.apptesis.model.PacienteModel


class PacientesAdapter(val listPacientes:List<PacienteModel>) :  RecyclerView.Adapter<PacientesAdapter.PacienteHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PacienteHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PacienteHolder(layoutInflater.inflate(R.layout.item_paciente, parent, false))

    }
    override fun getItemCount(): Int = listPacientes.size

    override fun onBindViewHolder(holder: PacienteHolder, position: Int) {
        holder.render(listPacientes[position])
    }


    class PacienteHolder(val view: View):RecyclerView.ViewHolder(view){

            val arrow = view.findViewById(R.id.arrowRecycler) as LottieAnimationView
            val tvName = view.findViewById(R.id.tvName) as TextView
            val tvTemp = view.findViewById(R.id.tvTemp) as TextView
            val tvHr = view.findViewById(R.id.tvHr) as TextView
            val tvSpo2 = view.findViewById(R.id.tvSpo2) as TextView
            val infoTab = view.findViewById(R.id.infoTab) as LinearLayout
        fun render(paciente : PacienteModel)
        {
                tvName.text= paciente.nombre +" "+ paciente.apellido
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
