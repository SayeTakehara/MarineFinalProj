package com.example.marinefinalproj

import android.animation.Animator
import android.os.Bundle
import android.view.*
import android.view.animation.CycleInterpolator
import android.view.animation.LinearInterpolator
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.NavGraph
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.marinefinalproj.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {
    private var _binding : FragmentTitleBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTitleBinding.inflate(inflater, container, false)
        val rootView = binding.root
        binding.playButton.setOnClickListener{
            var randomNum: Int = (Math.random() * 3).toInt()
            var action: NavDirections = TitleFragmentDirections.actionTitleFragmentToMinigameOneFragment()
            when(randomNum){
                0 -> action = TitleFragmentDirections.actionTitleFragmentToMinigameOneFragment()
                1 -> action = TitleFragmentDirections.actionTitleFragmentToMinigameTwoFragment()
                2 -> action = TitleFragmentDirections.actionTitleFragmentToMinigameThreeFragment()
            }
            rootView.findNavController().navigate(action)
        }
        binding.factsButton.setOnClickListener{
            val action = TitleFragmentDirections.actionTitleFragmentToFactPageFragment()
            rootView.findNavController().navigate(action)
        }
        binding.bubbleImage.y = 2000f
        binding.titleText.alpha = 0f
        binding.playButton.alpha = 0f
        binding.factsButton.alpha = 0f
        binding.bubbleImage.animate()
            .translationY(-2000f)
            .setDuration(1200)
            .withEndAction {
                binding.titleText.animate()
                    .alpha(1f)
                    .setDuration(700)
                    .start()
                binding.playButton.animate()
                    .alpha(1f)
                    .setDuration(700)
                    .start()
                binding.factsButton.animate()
                    .alpha(1f)
                    .setDuration(700)
                    .start()
            }
            .start()
        setHasOptionsMenu(true)
        return rootView
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu_all, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.
        onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
}