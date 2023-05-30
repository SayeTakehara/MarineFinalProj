package com.example.marinefinalproj

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.*
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.marinefinalproj.databinding.FragmentFactPageComplexBinding


class FactPageComplexFragment : Fragment() {
    private var _binding : FragmentFactPageComplexBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FactViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFactPageComplexBinding.inflate(inflater, container, false)
        val rootView = binding.root
        val randomOne = (Math.random() * 50).toLong()
        val randomTwo = (Math.random() * 50).toLong()
        val randomThree = (Math.random() * 50).toLong()
        animateText(binding.textFishOne, randomOne)
        animateText(binding.textFishTwo, randomTwo)
        animateText(binding.textFishThree, randomThree)
        animateFish(binding.fishImageOne, randomOne)
        animateFish(binding.fishImageTwo, randomTwo)
        animateFish(binding.fishImageThree, randomThree)
        viewModel.assignLastThree()
        val lastThree = viewModel.lastThreeFacts
        binding.textFishOne.text = if(lastThree.size > 0){
            lastThree[0]
        }
        else{
            ""
        }
        binding.textFishTwo.text = if(lastThree.size > 1){
            lastThree[1]
        }
        else{
            ""
        }
        binding.textFishThree.text = if(lastThree.size > 2){
            lastThree[2]
        }
        else{
            ""
        }
        binding.backtotitlebutton.setOnClickListener {
            rootView.findNavController().navigate(FactPageComplexFragmentDirections.actionFactPageFragmentToTitleFragment())
        }
        setHasOptionsMenu(true)
        return rootView
    }

    fun animateText(view: TextView, random: Long){
        val animationView: ViewPropertyAnimator = view.animate()
            .translationX(
                if(view.translationX < 0){
                    500f
                }
                else{
                    -500f
                }
            )
            .setInterpolator(LinearInterpolator())
            .setDuration(1200)
        animationView.setListener(object: AnimatorListenerAdapter(){
            override fun onAnimationEnd(animation: Animator) {
                view.animate()
                    .translationX(
                        if(view.translationX < 0){
                            500f
                        }
                        else {
                            -500f
                        }
                    )
                    .setInterpolator(LinearInterpolator())
                    .setListener(this)
                    .setStartDelay(random)
                    .setDuration(1200)
                    .start()
            }
        })
    }
    fun animateFish(view: ImageView, random: Long){
        val animationView: ViewPropertyAnimator = view.animate()
            .translationX(
                if(view.x < 0){
                    500f
                }
                else{
                    -500f
                }
            )
            .setInterpolator(LinearInterpolator())
            .setDuration(1200)
        animationView.setListener(object: AnimatorListenerAdapter(){
            override fun onAnimationEnd(animation: Animator) {
                view.animate()
                    .translationX(
                        if(view.x < 0){
                            500f
                        }
                        else{
                            -500f
                        }
                    )
                    .setInterpolator(LinearInterpolator())
                    .setListener(this)
                    .setStartDelay(random)
                    .setDuration(1200)
                    .start()
                view.rotationY += 180f
            }
        })
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu_complex, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.
        onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
}