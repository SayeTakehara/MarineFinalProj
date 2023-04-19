package com.example.marinefinalproj

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.NavGraph
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.marinefinalproj.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {
    private var _binding : FragmentTitleBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTitleBinding.inflate(inflater, container, false)
        val rootView = binding.root
        binding.playButton.setOnClickListener{
            var randomNum: Int = (Math.random() * 3).toInt()
            var action: NavDirections = TitleFragmentDirections.actionTitleFragmentToMinigameOneFragment()
            when(randomNum){
                0 -> action = TitleFragmentDirections.actionTitleFragmentToMinigameOneFragment()
                1 -> action = TitleFragmentDirections.actionTitleFragmentToMinigameTwoFragment()
                2 -> action = TitleFragmentDirections.actionTitleFragmentToMinigameThreeFragment()
            }
            rootView.findNavController().navigate(action)
        }
        return rootView
    }
}