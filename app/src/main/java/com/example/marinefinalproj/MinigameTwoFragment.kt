package com.example.marinefinalproj

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.example.marinefinalproj.databinding.FragmentMinigameTwoBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class MinigameTwoFragment : Fragment() {
    private var _binding : FragmentMinigameTwoBinding? = null
    private val binding get() = _binding!!
    private var timesFlinged = 0
    private val gesture = GestureDetector(
        activity,
        object : GestureDetector.SimpleOnGestureListener() {
            override fun onDown(e: MotionEvent): Boolean {
                return true
            }

            override fun onFling(
                e1: MotionEvent, e2: MotionEvent, velocityX: Float,
                velocityY: Float
            ): Boolean {
                timesFlinged++
                binding.sandBox1.text = timesFlinged.toString()
                binding.sandAnimation.visibility = View.VISIBLE
                binding.sandAnimation.animate()
                    .translationXBy(
                        if (velocityX > 0){
                            300f
                        }
                        else{
                            -300f
                        }
                    )
                    .alpha(0f)
                    .withEndAction{
                        if(timesFlinged >= 7){
                            if(timesFlinged >= 7){
                                lateinit var action: NavDirections
                                MaterialAlertDialogBuilder(requireContext())
                                    .setTitle(getString(R.string.sampleTextFact))
                                    .setMessage(getString(R.string.smallMessageSmalltext))
                                    .setPositiveButton("Yes") { dialog, which ->
                                        action = MinigameTwoFragmentDirections.actionMinigameTwoFragmentToFactPageFragment()
                                        binding.root.findNavController().navigate(action)
                                    }
                                    .setNegativeButton("No"){ dialog, which ->
                                        action = MinigameTwoFragmentDirections.actionMinigameTwoFragmentToTitleFragment()
                                        binding.root.findNavController().navigate(action)
                                    }
                                    .show()
                            }
                        }
                        binding.sandAnimation.translationX = 0f
                        binding.sandAnimation.alpha = 1f
                        binding.sandAnimation.visibility = View.INVISIBLE
                    }
                    .setDuration(200)
                    .start()
                return super.onFling(e1, e2, velocityX, velocityY)
            }
        })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMinigameTwoBinding.inflate(inflater, container, false)
        val rootView = binding.root
        //Minigame two: fling something? Maybe sand
        binding.sandBox1.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent): Boolean {
                return gesture.onTouchEvent(event)
            }
        })
        return rootView
    }
}