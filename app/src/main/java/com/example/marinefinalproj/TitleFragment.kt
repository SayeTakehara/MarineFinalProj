package com.example.marinefinalproj

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.example.marinefinalproj.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {
    private var _binding : FragmentTitleBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FactViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTitleBinding.inflate(inflater, container, false)
        val rootView = binding.root
        binding.playButton.setOnClickListener{
            var randomNum: Int = (Math.random() * 4).toInt()
            var action: NavDirections = TitleFragmentDirections.actionTitleFragmentToMinigameOneFragment()
            when(randomNum){
                0 -> action = TitleFragmentDirections.actionTitleFragmentToMinigameOneFragment()
                1 -> action = TitleFragmentDirections.actionTitleFragmentToMinigameTwoFragment()
                2 -> action = TitleFragmentDirections.actionTitleFragmentToMinigameThreeFragment()
                3 -> action = TitleFragmentDirections.actionTitleFragmentToMinigameFourFragment()
            }
            rootView.findNavController().navigate(action)
        }
        binding.factsButton.setOnClickListener {
            if(viewModel._playedOrNot) {
                val action = TitleFragmentDirections.actionTitleFragmentToFactPageFragment()
                rootView.findNavController().navigate(action)
            }
            else{
                Toast.makeText(this.activity, getString(R.string.toastTexrt), Toast.LENGTH_SHORT).show()
            }
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
}