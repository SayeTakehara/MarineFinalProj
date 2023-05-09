package com.example.marinefinalproj

import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.animation.CycleInterpolator
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
            lastThree[0].factText.toString()
        }
        else{
            ""
        }
        binding.textFishTwo.text = if(lastThree.size > 1){
            lastThree[1].factText.toString()
        }
        else{
            ""
        }
        binding.textFishThree.text = if(lastThree.size > 2){
            lastThree[2].factText.toString()
        }
        else{
            ""
        }
        setHasOptionsMenu(true)
        return rootView
    }

    fun animateText(view: TextView){
        view.animate()
            .translationXBy(
                if(view.x.toFloat() > 0){
                    300f
                }
                else{
                    -300f
                }
            )
            .setInterpolator(CycleInterpolator((60000 / 1000).toFloat()))
            .setDuration(600000)
            .start()
    }
    fun animateFish(view: ImageView){
        view.animate()
            .translationXBy(
                if(view.x.toFloat() > 0){
                    300f
                }
                else{
                    -300f
                }
            )
            .rotationYBy(90f)
            .setInterpolator(CycleInterpolator((60000 / 1000).toFloat()))
            .setDuration(600000)
            .start()
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