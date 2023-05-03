package com.example.marinefinalproj

import android.content.res.Resources
import com.example.marinefinalproj.databinding.ListItemBinding
import androidx.recyclerview.widget.RecyclerView

class FactViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    private lateinit var currentFact: Fact

    fun bindFact(fact: Fact){
        currentFact = fact
        if(!currentFact.seenBefore) {
            binding.factText.text = Resources.getSystem().getString(currentFact.factTextID)
        }
    }
}