package com.example.marinefinalproj

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.example.marinefinalproj.databinding.FragmentMinigameThreeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MinigameThreeFragment : Fragment() {
    private var _binding : FragmentMinigameThreeBinding? = null
    private val binding get() = _binding!!
    private var totalScrolled = 0
    var dbRef : DatabaseReference = Firebase.database.reference
    private val viewModel: FactViewModel by activityViewModels()
    private val randomTimes = ((Math.random() * 50) + 2000).toInt()
    private val gesture = GestureDetector(
        activity,
        object : GestureDetector.SimpleOnGestureListener() {
            override fun onDown(e: MotionEvent): Boolean {
                return true
            }

            override fun onScroll(
                e1: MotionEvent,
                e2: MotionEvent,
                distanceX: Float,
                distanceY: Float
            ): Boolean {
                if(distanceY > 0) {
                    totalScrolled += distanceY.toInt()
                    if(totalScrolled < randomTimes) {
                        binding.background.animate()
                            .translationYBy(-distanceY * 2)
                            .start()
                    }
                    else{
                        val factChosen = viewModel.addAndAssignFacts(dbRef)
                        MaterialAlertDialogBuilder(requireContext())
                            .setTitle(factChosen)
                            .setMessage("play again?")
                            .setPositiveButton("Yes") { dialog, which ->
                                binding.root.findNavController()
                                    .navigate(MinigameThreeFragmentDirections.actionMinigameThreeFragmentToTitleFragment())
                            }
                            .setNegativeButton("No") { dialog, which ->
                                binding.root.findNavController()
                                    .navigate(MinigameThreeFragmentDirections.actionMinigameThreeFragmentToFactPageFragment())
                            }
                            .setCancelable(false)
                            .show()
                    }
                }
                return super.onScroll(e1, e2, distanceX, distanceY)
            }
        })
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMinigameThreeBinding.inflate(inflater, container, false)
        val rootView = binding.root
        //Minigame 3: Scroll down gesture, go to deep sea
        totalScrolled = 0
        binding.root.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent): Boolean {
                if (totalScrolled < randomTimes) {
                    return gesture.onTouchEvent(event)
                }
                return false
            }
        })
        return rootView
    }
}