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

            binding.vpflecha.setOnClickListener{
                if(binding.vpinfo.visibility==View.GONE)
                {
                    binding.vpinfo.visibility=View.VISIBLE
                    binding.linearLayout2.setBackgroundResource(R.drawable.titlerecycler)
                    binding.indicator.visibility=View.VISIBLE
                    binding.vpflecha.setMinAndMaxProgress(0.0f,0.5f)
                    binding.vpflecha.playAnimation()
                }
                else{
                    binding.linearLayout2.setBackgroundResource(R.drawable.tittleinfodismiss)
                    binding.vpinfo.visibility=View.GONE
                    binding.indicator.visibility=View.GONE
                    binding.vpflecha.setMinAndMaxProgress(0.5f,1.0f)
                    binding.vpflecha.playAnimation()
                }

            }

            binding.tvtittle.text = info.titulo
            binding.vpinfo.adapter = VPInfoAdapter(info.list)
            binding.vpinfo.orientation= ViewPager2.ORIENTATION_HORIZONTAL
            binding.indicator.setViewPager(binding.vpinfo)

        }

    }


}