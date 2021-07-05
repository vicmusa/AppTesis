package com.example.apptesis

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.*
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.formatter.IValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.utils.ViewPortHandler
import com.google.firebase.database.*
import com.google.firebase.database.FirebaseDatabase.*
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.type.DateTime
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat

class TimeLineActivity : AppCompatActivity() {
    private var list = mutableListOf<Historical_data>()
    private var listDato = mutableListOf<Paciente>()
    private lateinit var db: FirebaseFirestore
    private lateinit var spinner: Spinner
    private lateinit var spinner2: Spinner
    private lateinit var textV1: TextView
    private val spo2Data: ArrayList<Entry> = arrayListOf()
    private val hrData: ArrayList<Entry> = arrayListOf()
    private val tempData: ArrayList<Entry> = arrayListOf()
    private var ts = arrayListOf<Long>()
    val dataSet: ArrayList<ILineDataSet> = arrayListOf()
    val dtbase = Firebase.database
    private lateinit var lineChart: LineChart
    private lateinit var ref: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_line)
        spinner = findViewById(R.id.spinner)
        spinner2 = findViewById(R.id.spinner2)
        val textV: TextView = findViewById(R.id.datos)
        textV1 = findViewById(R.id.textView)
        lineChart = findViewById(R.id.grafico)

        title = "Time Line"
        db = FirebaseFirestore.getInstance()
        ref = dtbase.getReference("Historicos")


        var datos = ""

        db.collection("pacientes")
                .whereNotEqualTo("idenUso", "ninguna")
                .get()
                .addOnSuccessListener { resultado ->
                    for (documento in resultado) {
                        // Obtengo todos los documentos que tienen ID y los inserto en una lista
                        val ci = documento.id
                        val name = documento["nombre"].toString()
                        val ap = documento["apellido"].toString()
                        val id = documento["idenUso"].toString()
                        val paciente = Paciente(name, ci, ap, id)
                        listDato.add(paciente)
                        datos += "${documento.id}: ${documento["nombre"]}\n"

                    }
                    val arraAdapter: ArrayAdapter<Paciente> = ArrayAdapter(this, android.R.layout.simple_spinner_item, listDato)
                    textV.text = datos
                    spinner.adapter = arraAdapter


                }
    }

    private fun buscardatos(id: String) {


        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    list.clear()
                    for (ds in snapshot.children) {

                        val ts = ds.key.toString().toLong()
                        val spo2 = ds.child("spo2").value.toString().toFloat()
                        val hr: Float = ds.child("hr").value.toString().toFloat()
                        val temp = ds.child("temp").value.toString().toFloat()
                        list.add(Historical_data(ts, spo2, hr, temp))
                    }
                } else {
                    Toast.makeText(this@TimeLineActivity, "EL ID NO EXISTE", Toast.LENGTH_LONG).show()
                }
            }


            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }
        ref.child(id).addListenerForSingleValueEvent(listener)
    }

    fun verGrafica(view: View) {


        val spId = spinner.selectedItemPosition
        textV1.text = listDato[spId].id
        buscardatos(listDato[spId].id)
        val arrayAdapter: ArrayAdapter<Historical_data> = ArrayAdapter(this@TimeLineActivity, android.R.layout.simple_dropdown_item_1line, list)
        spinner2.adapter = arrayAdapter
        graficar()

    }

    private fun graficar() {

        spo2Data.clear()
        hrData.clear()
        tempData.clear()
        ts.clear()

        for ((i, it) in list.withIndex()) {
            spo2Data.add(Entry(i.toFloat(), it.spo2))
            hrData.add(Entry(i.toFloat(), it.hr))
            tempData.add(Entry(i.toFloat(), it.temp))
            ts.add(it.ts)
            Log.e("Valores","${it.ts}")
        }

        var lineSpo2 = LineDataSet(spo2Data, "SPO2")
        var lineHr = LineDataSet(hrData, "HR")
        var lineTemp = LineDataSet(tempData, "Temp")
        lineSpo2.color = R.color.teal_700
        lineHr.color = R.color.red
        lineSpo2.color = R.color.blue

        Log.e("Valores","Estoy AQUI")
        dataSet.clear()
        dataSet.add(lineSpo2)
        dataSet.add(lineHr)
        dataSet.add(lineTemp)
        Log.e("Valores","Ahora AQUI")
        var valorX: XAxis = lineChart.xAxis
        valorX.labelCount = 0
        valorX.position=XAxis.XAxisPosition.BOTTOM
       // valorX.granularity = 1F

        valorX.valueFormatter = object : ValueFormatter(){
            override fun getFormattedValue(value: Float): String {
                val fdate= SimpleDateFormat("dd/MM/yy@HH:mm:ss")
                var date = fdate.format(Date((ts[value.toInt()]*1000)))
                return date.toString()
            }
        }

        val data: LineData = LineData(dataSet)
        lineChart.data = data
        lineChart.invalidate()


    }

}


