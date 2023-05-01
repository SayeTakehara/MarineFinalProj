package com.example.marinefinalproj

import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.animation.CycleInterpolator
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.marinefinalproj.databinding.FragmentFactPageComplexBinding

class FactPageComplexFragment : Fragment() {
    private var _binding : FragmentFactPageComplexBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFactPageComplexBinding.inflate(inflater, container, false)
        val rootView = binding.root
        setHasOptionsMenu(true)
        return rootView
    }

    fun animateObject(view: TextView){
        view.animate()
            .translationXBy(
                if(binding.textFishOne.x > 0){
                    300f
                }
                else{
                    -300f
                }
            )
            .setInterpolator(CycleInterpolator((60000 / 1000).toFloat()))
            .setDuration(600000)
            .setStartDelay((Math.random() * 1 + 3).toLong())
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