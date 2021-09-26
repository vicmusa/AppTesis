package com.example.apptesis

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.apptesis.databinding.ItemInfoBinding
import com.example.apptesis.model.InfoModel

class InfoAdapter(val infolist : List<InfoModel>,context: Context) :  RecyclerView.Adapter<InfoAdapter.InfoHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return InfoAdapter.InfoHolder(layoutInflater.inflate(R.layout.item_info, parent,false))
    }

    override fun onBindViewHolder(holder: InfoHolder, position: Int) {
        holder.render(infolist[position])
    }

    override fun getItemCount():Int = infolist.size


    class InfoHolder(val view: View):RecyclerView.ViewHolder(view){

        private val binding = ItemInfoBinding.bind(view)

        fun render(info : InfoModel){

            binding.tvtittle.text = info.titulo
            binding.vpinfo.adapter = VPInfoAdapter(info.list)
            binding.vpinfo.orientation= ViewPager2.ORIENTATION_HORIZONTAL
            binding.indicator.setViewPager(binding.vpinfo)

        }

    }


}