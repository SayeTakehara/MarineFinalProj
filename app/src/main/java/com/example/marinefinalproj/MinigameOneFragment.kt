package com.example.marinefinalproj

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.marinefinalproj.databinding.FragmentMinigameOneBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MinigameOneFragment : Fragment() {
    private var _binding : FragmentMinigameOneBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FactViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMinigameOneBinding.inflate(inflater, container, false)
        val rootView = binding.root
        // Minigame 1: Press a button multiple times, maybe fishing something up? YEPPERS
        var timesPressed = 0
        val random = ((Math.random() * 10) + 5).toInt()
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
                        val factChosen = viewModel.addAndAssignFacts()
                        MaterialAlertDialogBuilder(requireContext(), R.style.AltertDialog)
                            .setTitle(factChosen)
                            .setMessage("play again?")
                            .setPositiveButton("Yes") { dialog, which ->
                                rootView.findNavController().navigate(MinigameOneFragmentDirections.actionMinigameOneFragmentToTitleFragment())
                            }
                            .setNegativeButton("No"){ dialog, which ->
                                rootView.findNavController().navigate(MinigameOneFragmentDirections.actionMinigameOneFragmentToFactPageFragment())
                            }
                            .setCancelable(false)
                            .show()
                        timesPressed = 0
                    }
                    .setDuration(1000)
                    .start()
            }
        }
        return rootView
    }
}