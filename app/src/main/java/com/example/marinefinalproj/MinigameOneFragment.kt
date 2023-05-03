package com.example.marinefinalproj

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.marinefinalproj.databinding.FragmentMinigameOneBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MinigameOneFragment : Fragment() {
    private var _binding : FragmentMinigameOneBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMinigameOneBinding.inflate(inflater, container, false)
        val rootView = binding.root
        // Minigame 1: Press a button multiple times, maybe fishing something up? YEPPERS
        var timesPressed = 0
        val random = ((Math.random() * 20) + 10).toInt()
        binding.timesPressedText.text = timesPressed.toString()
        binding.reelImageButton.setOnClickListener {
            val gameFinished = timesPressed > random
            if(!gameFinished){
                timesPressed++
                binding.timesPressedText.visibility = View.VISIBLE
                binding.textbackground.visibility = View.VISIBLE
                binding.reelImageButton.animate()
                    .rotationBy(360f)
                    .setDuration(700)
                    .withEndAction{
                        binding.timesPressedText.visibility = View.INVISIBLE
                        binding.textbackground.visibility = View.INVISIBLE
                    }
                    .start()
            }
            binding.timesPressedText.text = timesPressed.toString()
            if(gameFinished){
                binding.fishImage.visibility = View.VISIBLE
                binding.fishImage.animate()
                    .translationY(-700f)
                    .withEndAction {
                        var action = MinigameOneFragmentDirections.actionMinigameOneFragmentToFactPageFragment()
                        MaterialAlertDialogBuilder(requireContext())
                            .setTitle(getString(R.string.sampleTextFact))
                            .setMessage(getString(R.string.smallMessageSmalltext))
                            .setPositiveButton("Yes") { dialog, which ->
                                action = MinigameOneFragmentDirections.actionMinigameOneFragmentToTitleFragment()
                            }
                            .setNegativeButton("No"){ dialog, which ->
                            }
                            .show()
                        timesPressed = 0
                        rootView.findNavController().navigate(action)
                    }
                    .setDuration(1000)
                    .start()
            }
        }
        return rootView
    }
}