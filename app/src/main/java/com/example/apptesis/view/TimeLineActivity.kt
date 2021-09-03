    package com.example.apptesis.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.apptesis.core.Pref
import com.example.apptesis.databinding.ActivityTimeLineBinding
import com.example.apptesis.model.HistoricalDataModel
import com.example.apptesis.model.PacienteModel
import com.example.apptesis.viewmodel.TimeLineViewModel
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.google.firebase.database.*
import com.google.firebase.database.FirebaseDatabase.*
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.sql.Date
import java.text.DateFormat.getDateTimeInstance
import java.text.SimpleDateFormat

class TimeLineActivity : AppCompatActivity() {
    private lateinit var binding : ActivityTimeLineBinding
    private var list = listOf<PacienteModel>()
    private val timeLineViewModel : TimeLineViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTimeLineBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var valorX = binding.grafico.xAxis
        var pref = Pref(this)
        timeLineViewModel.createSpinner(pref)
        timeLineViewModel.listToAdapter.observe(this, Observer {
            binding.progressBar2.visibility= View.GONE
            if(it.isEmpty())
            {
                binding.button3.visibility = View.GONE
            }
            val arrayAdapter: ArrayAdapter<PacienteModel> =
                ArrayAdapter(this, android.R.layout.simple_spinner_item, it)
            binding.spinner.adapter = arrayAdapter
            binding.datos.text = it.toString()
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
            timeLineViewModel.listTimeStamp.observe(this, Observer {
                valorX.labelCount = 0
                valorX.position = XAxis.XAxisPosition.BOTTOM
                valorX.valueFormatter = object : ValueFormatter() {
                    override fun getFormattedValue(value: Float): String {
                        val fdate = getDateTimeInstance()
                        var date = fdate.format(Date((it[value.toInt()] * 1000)))
                        return date.toString()
                    }
                }
            })
            binding.grafico.data = it
            binding.grafico.invalidate()
        })
        binding.button3.setOnClickListener { timeLineViewModel.graph(list[binding.spinner.selectedItemPosition].id,list[binding.spinner.selectedItemPosition].fecha) }
        title = "Time Line"
    }
    }




