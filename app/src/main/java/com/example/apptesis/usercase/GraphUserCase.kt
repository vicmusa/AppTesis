package com.example.apptesis.usercase

import android.content.Context
import android.graphics.Color
import android.util.Log
import androidx.core.content.ContextCompat.getColor
import com.example.apptesis.R
import com.example.apptesis.model.HistoricalDataModel
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet

class GraphUserCase {

    suspend operator fun invoke(list: List<HistoricalDataModel>,context: Context): LineData {
        val spo2Data: ArrayList<Entry> = arrayListOf()
        val hrData: ArrayList<Entry> = arrayListOf()
        val tempData: ArrayList<Entry> = arrayListOf()

        for ((i, it) in list.withIndex()) {
            spo2Data.add(Entry(i.toFloat(), it.spo2))
            hrData.add(Entry(i.toFloat(), it.hr))
            tempData.add(Entry(i.toFloat(), it.temp))
        }
        val lineSpo2 = LineDataSet(spo2Data, "SPO2")
        val lineHr = LineDataSet(hrData, "HR")
        val lineTemp = LineDataSet(tempData, "Temp")
        lineTemp.color = getColor(context,R.color.orange)
        lineHr.color = getColor(context,R.color.red)
        lineSpo2.color = getColor(context,R.color.blue)
        lineHr.setCircleColor(getColor(context,R.color.alice_blue))
        lineTemp.setCircleColor(getColor(context,R.color.alice_blue))
        lineSpo2.setCircleColor(getColor(context,R.color.alice_blue))



        val dataSet: ArrayList<ILineDataSet> = arrayListOf()
        dataSet.add(lineSpo2)
        dataSet.add(lineHr)
        dataSet.add(lineTemp)
        return LineData(dataSet)

    }
    }
