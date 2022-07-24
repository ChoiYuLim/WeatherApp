package com.yulim.weatherapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yulim.weatherapp.databinding.SigunguItemBinding

class sigunguAdapter : RecyclerView.Adapter<sigunguAdapter.sigunguViewHolder>() {
    val sigunguList = mutableListOf<sigunguData>()

    override fun getItemCount(): Int = sigunguList.size

    override fun onBindViewHolder(holder: sigunguViewHolder, position: Int) {
        holder.onBind(sigunguList[position])

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): sigunguViewHolder {
        val binding = SigunguItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return sigunguViewHolder(binding)
    }

    class sigunguViewHolder(private val binding: SigunguItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: sigunguData) {
            binding.tvItemSigungu.text = data.sigungu
        }
    }
}