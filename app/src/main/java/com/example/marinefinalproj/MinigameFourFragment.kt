package com.example.marinefinalproj

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.marinefinalproj.databinding.FragmentMinigameFourBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MinigameFourFragment : Fragment() {
    private var _binding : FragmentMinigameFourBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FactViewModel by activityViewModels()
    private var scaleFactor = 1f
    private val randomNumber: Int = (Math.random() * 49 + 51).toInt()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMinigameFourBinding.inflate(inflater, container, false)
        val rootView = binding.root
        //Minigame 4: zoom into some small creatures
        scaleFactor = 1f
        var gesture = ScaleGestureDetector(
            requireContext(),
            object: ScaleGestureDetector.SimpleOnScaleGestureListener() {
                override fun onScale(detector: ScaleGestureDetector): Boolean {
                    if(scaleFactor < randomNumber.toLong() + 5){
                        scaleFactor = scaleFactor * (detector.scaleFactor)
                        scaleFactor = Math.max(1f, Math.min(scaleFactor, 100.0f))
                        binding.smallcreature.scaleX = scaleFactor * 0.5f
                        binding.smallcreature.scaleY = scaleFactor * 0.5f
                        if(scaleFactor >= randomNumber.toLong()){
                            val factChosen = viewModel.addAndAssignFacts()
                            MaterialAlertDialogBuilder(requireContext())
                                .setTitle(factChosen)
                                .setMessage("play again?")
                                .setPositiveButton("Yes") { dialog, which ->
                                    binding.root.findNavController()
                                        .navigate(MinigameFourFragmentDirections.actionMinigameFourFragmentToTitleFragment())
                                    }
                                .setNegativeButton("No") { dialog, which ->
                                    binding.root.findNavController()
                                        .navigate(MinigameFourFragmentDirections.actionMinigameFourFragmentToFactPageFragment())
                                    }
                                .setCancelable(false)
                                .show()
                        }
                    }
                    return super.onScale(detector)
                }
            })
        binding.root.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent): Boolean {
                return gesture.onTouchEvent(event)
                return false
            }
        })
        return rootView
    }
}