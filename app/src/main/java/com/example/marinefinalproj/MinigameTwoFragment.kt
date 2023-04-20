package com.example.marinefinalproj

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.marinefinalproj.databinding.FragmentMinigameOneBinding
import com.example.marinefinalproj.databinding.FragmentMinigameTwoBinding

class MinigameTwoFragment : Fragment() {
    private var _binding : FragmentMinigameTwoBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMinigameTwoBinding.inflate(inflater, container, false)
        val rootView = binding.root
        //Minigame two: fling something? Maybe sand
        return rootView
    }
}