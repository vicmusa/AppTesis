    package com.example.apptesis.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.apptesis.R
import android.widget.*
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.apptesis.core.Pref
import com.example.apptesis.databinding.ActivityTimeLineBinding
import com.example.apptesis.model.PacienteModel
import com.example.apptesis.viewmodel.TimeLineViewModel
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.formatter.ValueFormatter
import com.google.firebase.database.*
import com.google.firebase.database.FirebaseDatabase.*
import www.sanju.motiontoast.MotionToast
import java.sql.Date
import java.text.DateFormat.getDateTimeInstance


class TimeLineActivity : AppCompatActivity() {
    private lateinit var binding : ActivityTimeLineBinding
    private var list = listOf<PacienteModel>()
    private val timeLineViewModel : TimeLineViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTimeLineBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prepareActionBar()
        binding.grafico.setDrawBorders(true)
        val descri = Description()
        descri.text="Evolución"
        descri.textColor = R.color.bluelite
        binding.grafico.description = descri
        var valorX = binding.grafico.xAxis
        var pref = Pref(this)
        timeLineViewModel.createSpinner(pref)
        timeLineViewModel.listToAdapter.observe(this, Observer {
            binding.progressBar2.visibility= View.GONE
            if(it.isEmpty())
            {
                binding.button3.visibility = View.GONE
                binding.spinner.visibility = View.GONE
                MotionToast.createColorToast(this,getString(R.string.advertencia),"No hay pacientes agregados",MotionToast.TOAST_WARNING,MotionToast.GRAVITY_BOTTOM,MotionToast.LONG_DURATION,null)
            }
            val arrayAdapter: ArrayAdapter<PacienteModel> =
                ArrayAdapter(this, android.R.layout.simple_spinner_item, it)
            binding.spinner.adapter = arrayAdapter
            list = it
        })
        timeLineViewModel.isLoading.observe(this, Observer {
            if (it) {
                binding.grafico.visibility = View.GONE

            } else {
                binding.grafico.visibility = View.VISIBLE
            }
        })
        timeLineViewModel.dataGraph.observe(this, Observer {
            timeLineViewModel.listTimeStamp.observe(this, Observer { list ->
                if(list.isEmpty())
                {
                    MotionToast.createColorToast(this,getString(R.string.error),"No hay datos para el paciente",MotionToast.TOAST_INFO,MotionToast.GRAVITY_BOTTOM,MotionToast.LONG_DURATION,null)
                    binding.nodatatv.visibility = View.VISIBLE
                }
                else{
                var label = 5
                if(label< 5 )
                {
                    label = 2
                }
                valorX.labelCount = label
                valorX.position = XAxis.XAxisPosition.BOTTOM
                valorX.valueFormatter = object : ValueFormatter() {
                    override fun getFormattedValue(value: Float): String {
                        val fdate = getDateTimeInstance()
                        var date = fdate.format(Date((list[value.toInt()] * 1000)))
                        return date.toString()
                    }
                }
            }
            })
            binding.tvNombreP.visibility = View.VISIBLE
            binding.tvNombreP.text=binding.spinner.selectedItem.toString()
            binding.grafico.data = it
            binding.grafico.invalidate()
        })
        binding.button3.setOnClickListener {
            binding.nodatatv.visibility=View.GONE
            timeLineViewModel.graph(list[binding.spinner.selectedItemPosition].id,list[binding.spinner.selectedItemPosition].fecha,this)
            binding.grafico.fitScreen()
        }
        title = "Evolucíon"
    }

    private fun prepareActionBar() {
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    }




