package com.example.marinefinalproj

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.*
import android.view.animation.LinearInterpolator
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.marinefinalproj.databinding.FragmentFactPageSimpleBinding

class FactPageSimpleFragment : Fragment() {
    private var _binding : FragmentFactPageSimpleBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FactViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFactPageSimpleBinding.inflate(inflater, container, false)
        val rootView = binding.root
        val sampleFacts = viewModel.shownFacts

        val myAdapter = FactAdapter(sampleFacts)
        binding.recyclerView.adapter = myAdapter
        setHasOptionsMenu(true)
        return rootView
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.
        onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu_simple, menu)
    }
}