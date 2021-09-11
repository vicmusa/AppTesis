package com.example.apptesis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import com.example.apptesis.databinding.ActivityInfoBinding

class InfoActivity : AppCompatActivity() {
    private lateinit var binding : ActivityInfoBinding
    private var URL = "https://www.who.int/es/emergencies/diseases/novel-coronavirus-2019/question-and-answers-hub/q-a-detail/coronavirus-disease-covid-19"
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityInfoBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.wview.webChromeClient = object : WebChromeClient(){}
        binding.wview.webViewClient = object : WebViewClient(){}
        val settings = binding.wview.settings
        settings.javaScriptEnabled = true


        binding.tvmoreinfo.setOnClickListener {
            binding.wview.visibility = View.VISIBLE
            binding.wview.loadUrl(URL)
        }


    }

    override fun onBackPressed() {
        if(binding.wview.canGoBack())
        {
            binding.wview.goBack()
        }
        else{
            if(binding.wview.visibility == View.VISIBLE)
            {
                binding.wview.visibility = View.GONE
            }
            else
            {
                super.onBackPressed()
            }
        }

    }
}