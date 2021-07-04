package com.example.apptesis.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.apptesis.R

class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters







    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val comp : View
        comp=inflater.inflate(R.layout.fragment_home, container, false)
        return comp
    }


}