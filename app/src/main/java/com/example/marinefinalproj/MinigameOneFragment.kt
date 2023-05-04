package com.example.marinefinalproj

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.example.marinefinalproj.databinding.FragmentMinigameOneBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MinigameOneFragment : Fragment() {
    private var _binding : FragmentMinigameOneBinding? = null
    private val binding get() = _binding!!
    lateinit var dbRef : DatabaseReference
    private val viewModel: FactViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMinigameOneBinding.inflate(inflater, container, false)
        val rootView = binding.root
        dbRef = Firebase.database.reference
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
                        var action: NavDirections
                        val factText = getString(viewModel.addRandomFact(dbRef))
                        val yesOrNo = viewModel.alertDialog(requireContext(), dbRef, factText)
                        if(yesOrNo){
                            action = MinigameOneFragmentDirections.actionMinigameOneFragmentToTitleFragment()
                            rootView.findNavController().navigate(action)
                        }
                        else{
                            action = MinigameOneFragmentDirections.actionMinigameOneFragmentToFactPageFragment()
                            rootView.findNavController().navigate(action)
                        }
                        timesPressed = 0
                    }
                    .setDuration(1000)
                    .start()
            }
        }
        return rootView
    }
}