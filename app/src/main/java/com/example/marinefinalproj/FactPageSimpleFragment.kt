package com.example.marinefinalproj

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.marinefinalproj.databinding.FragmentFactPageSimpleBinding

class FactPageSimpleFragment : Fragment() {
    private var _binding : FragmentFactPageSimpleBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFactPageSimpleBinding.inflate(inflater, container, false)
        val rootView = binding.root

        val sampleFacts = listOf<Fact>(
            Fact("Randy Jellicent is actually a survivor of the titanic", false),
            Fact("Randy Jellicent's book is out NOW", false),
            Fact("Randy Jellicent holds the world Record for the most amount of jellies stung", false)
        )
        val myAdapter = FactAdapter(sampleFacts)
        binding.recyclerView.adapter = myAdapter
        setHasOptionsMenu(true)
        return rootView
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu_simple, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.
        onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
}