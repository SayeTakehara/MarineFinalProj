package com.example.marinefinalproj

import com.example.marinefinalproj.databinding.ListItemBinding
import androidx.recyclerview.widget.RecyclerView

class FactViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    private lateinit var currentFact: String

    fun bindFact(fact: String){
        currentFact = fact
        binding.factText.text = currentFact
    }
}