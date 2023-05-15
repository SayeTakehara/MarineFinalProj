package com.example.marinefinalproj

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marinefinalproj.databinding.ListItemBinding

class FactAdapter (val factList: List<String>) : RecyclerView.Adapter<FactViewHolder>(){
    override fun getItemCount(): Int {
        return factList.size
    }

    override fun onBindViewHolder(holder: FactViewHolder, position: Int) {
        val currentFact = factList[position]
        holder.bindFact(currentFact)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FactViewHolder(binding)
    }

}