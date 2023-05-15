package com.example.marinefinalproj

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.*
import android.view.animation.CycleInterpolator
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
        animateText(binding.textFishOne)
        animateText(binding.textFishTwo)
        animateText(binding.textFishThree)
        animateFish(binding.fishImageOne)
        animateFish(binding.fishImageTwo)
        animateFish(binding.fishImageThree)
        viewModel.assignLastThree()
        val lastThree = viewModel.lastThreeFacts
        binding.textFishOne.text = if(lastThree.size > 0){
            lastThree[0].toString()
        }
        else{
            ""
        }
        binding.textFishTwo.text = if(lastThree.size > 1){
            lastThree[1].toString()
        }
        else{
            ""
        }
        binding.textFishThree.text = if(lastThree.size > 2){
            lastThree[2].toString()
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

    fun animateText(view: TextView){
        val animationView: ViewPropertyAnimator = view.animate()
            .translationX(
                if(view.translationX.toFloat() < 0){
                    300f
                }
                else{
                    -300f
                }
            )
            .setInterpolator(LinearInterpolator())
            .setDuration(900)
        animationView.setListener(object: AnimatorListenerAdapter(){
            override fun onAnimationEnd(animation: Animator) {
                view.animate()
                    .translationX(
                        if(view.translationX.toFloat() < 0){
                            300f
                        }
                        else if(view.translationX.toFloat() > 0) {
                            -300f
                        }
                        else{
                            0f
                        }
                    )
                    .setInterpolator(LinearInterpolator())
                    .setListener(this)
                    .setDuration(900)
                    .start()
            }
        })
    }
    fun animateFish(view: ImageView){
        val animationView: ViewPropertyAnimator = view.animate()
            .translationX(
                if(view.x.toFloat() < 0){
                    300f
                }
                else{
                    -300f
                }
            )
            .setInterpolator(LinearInterpolator())
            .setDuration(900)
        animationView.setListener(object: AnimatorListenerAdapter(){
            override fun onAnimationEnd(animation: Animator) {
                view.animate()
                    .translationX(
                        if(view.x.toFloat() < 0){
                            300f
                        }
                        else{
                            -300f
                        }
                    )
                    .setInterpolator(LinearInterpolator())
                    .setListener(this)
                    .setDuration(900)
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