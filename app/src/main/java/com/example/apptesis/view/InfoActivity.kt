package com.example.apptesis.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import com.example.apptesis.InfoAdapter
import com.example.apptesis.databinding.ActivityInfoBinding
import com.example.apptesis.model.Repository

class InfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInfoBinding
    private val list = listOf(Repository.recomendaciones, Repository.sintomas)
    private var URL =
        "https://www.who.int/es/emergencies/diseases/novel-coronavirus-2019/question-and-answers-hub/q-a-detail/coronavirus-disease-covid-19"

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityInfoBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "Información"
        prepareActionBar()
        binding.wview.webChromeClient = object : WebChromeClient() {}
        binding.wview.webViewClient = object : WebViewClient() {}
        val settings = binding.wview.settings
        settings.javaScriptEnabled = true

        val info = InfoAdapter(list, this)
        binding.rvinfo.adapter = info
        binding.tvmoreinfo.setOnClickListener {
            binding.wview.visibility = View.VISIBLE
            binding.wview.loadUrl(URL)
            binding.lvtotal.visibility = View.GONE
        }


    }

    override fun onBackPressed() {
        if (binding.wview.canGoBack()) {
            binding.wview.goBack()
        } else {
            if (binding.wview.visibility == View.VISIBLE) {
                binding.wview.visibility = View.GONE
                binding.lvtotal.visibility = View.VISIBLE
            } else {
                super.onBackPressed()
            }
        }

    }

    private fun prepareActionBar() {
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}