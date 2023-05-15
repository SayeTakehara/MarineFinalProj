package com.example.marinefinalproj

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.example.marinefinalproj.databinding.FragmentMinigameTwoBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class MinigameTwoFragment : Fragment() {
    private var _binding : FragmentMinigameTwoBinding? = null
    private val binding get() = _binding!!
    private var timesFlinged = 0
    private val randomTimes = ((Math.random() * 10) + 7).toInt()
    var dbRef : DatabaseReference = Firebase.database.reference
    private val viewModel: FactViewModel by activityViewModels()
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
                binding.timesFlickedText.text = timesFlinged.toString()
                binding.textFlickedBackground.visibility = View.VISIBLE
                binding.timesFlickedText.visibility = View.VISIBLE
                binding.sandAnimation.visibility = View.VISIBLE
                binding.sandAnimation.animate()
                    .translationXBy(
                        if (velocityX > 0){
                            binding.sandAnimation.rotationY = 0f
                            300f
                        }
                        else{
                            binding.sandAnimation.rotationY = -900f
                            -300f
                        }
                    )
                    .alpha(0f)
                    .withEndAction {
                        binding.textFlickedBackground.visibility = View.INVISIBLE
                        binding.timesFlickedText.visibility = View.INVISIBLE
                        if (timesFlinged >= randomTimes) {
                            val factChosen = viewModel.addAndAssignFacts(dbRef)
                            MaterialAlertDialogBuilder(requireContext())
                                .setTitle(factChosen)
                                .setMessage("play again?")
                                .setPositiveButton("Yes") { dialog, which ->
                                    binding.root.findNavController()
                                        .navigate(MinigameTwoFragmentDirections.actionMinigameTwoFragmentToTitleFragment())
                                }
                                .setNegativeButton("No") { dialog, which ->
                                    binding.root.findNavController()
                                        .navigate(MinigameTwoFragmentDirections.actionMinigameTwoFragmentToFactPageFragment())
                                }
                                .setCancelable(false)
                                .show()
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
                if(timesFlinged < randomTimes) {
                    return gesture.onTouchEvent(event)
                }
                return false
            }
        })
        return rootView
    }
}