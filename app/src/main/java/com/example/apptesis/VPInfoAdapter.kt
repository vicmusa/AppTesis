package com.example.apptesis

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.apptesis.databinding.ItemInfoBinding
import com.example.apptesis.databinding.ItemViewpagerBinding
import com.example.apptesis.model.InfoModel
import com.example.apptesis.model.InnerInfoModel

class VPInfoAdapter (val infolist : List<InnerInfoModel>) :  RecyclerView.Adapter<VPInfoAdapter.VPInfoHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VPInfoAdapter.VPInfoHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return VPInfoAdapter.VPInfoHolder(layoutInflater.inflate(R.layout.item_viewpager, parent,false))
    }

    override fun onBindViewHolder(holder: VPInfoAdapter.VPInfoHolder, position: Int) {
        holder.render(infolist[position])
    }

    override fun getItemCount():  Int = infolist.size

     class  VPInfoHolder(val view: View):RecyclerView.ViewHolder(view){

         private val binding = ItemViewpagerBinding.bind(view)

         fun render(info : InnerInfoModel){

             binding.vpdescription.text = info.description
             binding.vptittle.text = info.titulo
             binding.vpanimation.setAnimation(info.Imagen)


         }

}
}