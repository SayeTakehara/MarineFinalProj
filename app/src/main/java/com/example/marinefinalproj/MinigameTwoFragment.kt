package com.example.marinefinalproj

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.GestureDetectorCompat
import androidx.core.view.MotionEventCompat
import androidx.fragment.app.Fragment
import com.example.marinefinalproj.databinding.FragmentMinigameTwoBinding


class MinigameTwoFragment : Fragment() {
    private var _binding : FragmentMinigameTwoBinding? = null
    private val binding get() = _binding!!
    private lateinit var mDetector: GestureDetector

    private var gestureListener: GestureDetector.SimpleOnGestureListener = object: GestureDetector.SimpleOnGestureListener(){
        override fun onFling(
            e1: MotionEvent,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            return super.onFling(e1, e2, velocityX, velocityY)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMinigameTwoBinding.inflate(inflater, container, false)
        val rootView = binding.root
        //Minigame two: fling something? Maybe sand
        GestureDetector.SimpleOnGestureListener()
        mDetector = GestureDetector(this.requireContext(), gestureListener)
        return rootView
    }
}