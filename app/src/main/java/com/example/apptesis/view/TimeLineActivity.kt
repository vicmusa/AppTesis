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
        binding.graficoO2.setDrawBorders(true)
        binding.graficoHr.setDrawBorders(true)
        binding.graficotemp.setDrawBorders(true)
        val descri  = Description()
        val descri2 = Description()
        val descri3 = Description()
        descri2.text="Temperatura"
        descri2.textColor = R.color.bluelite
        descri3.text="SpO2"
        descri3.textColor = R.color.bluelite
        descri.text="Ritmo Cárdiaco"
        descri.textColor = R.color.bluelite
        binding.graficoHr.description = descri
        binding.graficotemp.description = descri2
        binding.graficoO2.description = descri3
        val valorX = binding.graficoHr.xAxis
        val valorX1 = binding.graficoO2.xAxis
        val valorX2 = binding.graficotemp.xAxis
        val pref = Pref(this)
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
                ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, it)
            binding.spinner.adapter = arrayAdapter
            list = it
        })
        timeLineViewModel.nodata.observe(this,{
            MotionToast.createColorToast(this,getString(R.string.error),"No hay datos para el paciente",MotionToast.TOAST_INFO,MotionToast.GRAVITY_BOTTOM,MotionToast.LONG_DURATION,null)
        })
        timeLineViewModel.isLoading.observe(this, Observer {
            if (it) {
                binding.llgraph.visibility= View.GONE

            } else {
                binding.llgraph.visibility = View.VISIBLE
            }
        })
        timeLineViewModel.dataGraph.observe(this, Observer {
            timeLineViewModel.listTimeStamp.observe(this, Observer { list ->
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
                valorX1.labelCount = label
                valorX1.position = XAxis.XAxisPosition.BOTTOM
                valorX1.valueFormatter = object : ValueFormatter() {
                    override fun getFormattedValue(value: Float): String {
                        val fdate = getDateTimeInstance()
                        var date = fdate.format(Date((list[value.toInt()] * 1000)))
                        return date.toString()
                    }
                }
                valorX2.labelCount = label
                valorX2.position = XAxis.XAxisPosition.BOTTOM
                valorX2.valueFormatter = object : ValueFormatter() {
                    override fun getFormattedValue(value: Float): String {
                        val fdate = getDateTimeInstance()
                        var date = fdate.format(Date((list[value.toInt()] * 1000)))
                        return date.toString()
                    }
                }

            })
            binding.tvNombreP.visibility = View.VISIBLE
            binding.tvNombreP.text=binding.spinner.selectedItem.toString()
            binding.graficoO2.data = it[1]
            binding.graficoO2.invalidate()
            binding.graficotemp.data = it[2]
            binding.graficotemp.invalidate()
            binding.graficoHr.data = it[0]
            binding.graficoHr.invalidate()
        })
        binding.button3.setOnClickListener {
            timeLineViewModel.graph(list[binding.spinner.selectedItemPosition].id,list[binding.spinner.selectedItemPosition].fecha,this)
            binding.llgraph.visibility = View.GONE
            binding.graficotemp.fitScreen()
            binding.graficoO2.fitScreen()
            binding.graficoHr.fitScreen()
        }
        title = "Evolucíon"
    }

    private fun prepareActionBar() {
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }
    }




