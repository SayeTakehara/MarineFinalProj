package com.example.marinefinalproj

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.marinefinalproj.databinding.FragmentMinigameThreeBinding

class MinigameThreeFragment : Fragment() {
    private var _binding : FragmentMinigameThreeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMinigameThreeBinding.inflate(inflater, container, false)
        val rootView = binding.root
        return rootView
    }
}