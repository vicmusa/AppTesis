package com.example.apptesis.usercase

import android.util.Log
import com.example.apptesis.R
import com.example.apptesis.model.HistoricalDataModel
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet

class GraphUserCase {

    suspend operator fun invoke(list: List<HistoricalDataModel>): LineData {
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
        lineSpo2.color = R.color.orange
        lineHr.color = R.color.red
        lineSpo2.color = R.color.blue
        val dataSet: ArrayList<ILineDataSet> = arrayListOf()
        dataSet.add(lineSpo2)
        dataSet.add(lineHr)
        dataSet.add(lineTemp)
        return LineData(dataSet)

    }
    }
