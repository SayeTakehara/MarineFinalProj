package com.example.marinefinalproj

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.marinefinalproj.databinding.FragmentMinigameThreeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MinigameThreeFragment : Fragment() {
    private var _binding : FragmentMinigameThreeBinding? = null
    private val binding get() = _binding!!
    private var totalScrolled = 0
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
                    if(totalScrolled >= 2000){
                        var action = MinigameThreeFragmentDirections.actionMinigameThreeFragmentToTitleFragment()
                        MaterialAlertDialogBuilder(requireContext())
                            .setTitle(getString(R.string.sampleTextFact))
                            .setMessage(getString(R.string.smallMessageSmalltext))
                            .setPositiveButton("Yes") { dialog, which ->
                                action = MinigameThreeFragmentDirections.actionMinigameThreeFragmentToFactPageFragment()
                            }
                            .setNegativeButton("No"){ dialog, which ->
                            }
                            .show()
                        binding.root.findNavController().navigate(action)
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
        binding.root.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent): Boolean {
                return gesture.onTouchEvent(event)
            }
        })
        return rootView
    }
}