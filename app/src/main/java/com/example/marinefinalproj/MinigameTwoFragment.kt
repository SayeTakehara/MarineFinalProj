package com.example.marinefinalproj

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.view.GestureDetectorCompat
import androidx.fragment.app.Fragment
import com.example.marinefinalproj.databinding.FragmentMinigameOneBinding
import com.example.marinefinalproj.databinding.FragmentMinigameTwoBinding
import android.view.GestureDetector
import android.view.MotionEvent

class MinigameTwoFragment : Fragment() {
    private var _binding : FragmentMinigameTwoBinding? = null
    private val binding get() = _binding!!
    private lateinit var mDetector: GestureDetectorCompat
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMinigameTwoBinding.inflate(inflater, container, false)
        val rootView = binding.root
        //Minigame two: fling something? Maybe sand
        mDetector = GestureDetectorCompat(this.requireContext(), MyGestureListener())
        return rootView
    }


    private class MyGestureListener : GestureDetector.SimpleOnGestureListener() {

        override fun onFling(
            event1: MotionEvent,
            event2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            Log.d("MainActivity", "onFling: $event1 $event2")
            return true
        }
    }
}