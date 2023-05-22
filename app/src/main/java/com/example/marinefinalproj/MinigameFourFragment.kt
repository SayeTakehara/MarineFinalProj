package com.example.marinefinalproj

import android.os.Bundle
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
    var dbRef : DatabaseReference = Firebase.database.reference
    private val viewModel: FactViewModel by activityViewModels()
    private var scaleFactor = 1f
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMinigameFourBinding.inflate(inflater, container, false)
        val rootView = binding.root
        //Minigame 4: zoom into some zooplankton
        var gesture = ScaleGestureDetector(
            requireContext(),
            object: ScaleGestureDetector.SimpleOnScaleGestureListener() {
                override fun onScale(detector: ScaleGestureDetector): Boolean {
                    scaleFactor = scaleFactor * (detector.scaleFactor * 0.5f)
                    scaleFactor = Math.max(0.1f, Math.min(scaleFactor, 5.0f))
                    binding.imageView.scaleX = scaleFactor
                    binding.imageView.scaleY = scaleFactor
                    if(scaleFactor == 3.0f){
                        val factChosen = viewModel.addAndAssignFacts(dbRef)
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