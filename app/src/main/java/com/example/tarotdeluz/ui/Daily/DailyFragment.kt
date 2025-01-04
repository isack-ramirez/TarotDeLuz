package com.example.tarotdeluz.ui.Daily

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.tarotdeluz.databinding.FragmentDailyBinding

class DailyFragment : Fragment() {

    private var _binding: FragmentDailyBinding? = null
    private val binding get() = _binding!!
    private lateinit var dailyViewModel: DailyViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDailyBinding.inflate(inflater, container, false)
        val root: View = binding.root

        dailyViewModel = ViewModelProvider(this)[DailyViewModel::class.java]

        // Set up button click listener
        binding.drawCardButton.setOnClickListener {
            dailyViewModel.performDrawDaily()
        }

        // Observe card information changes
        dailyViewModel.cardInfo.observe(viewLifecycleOwner) { cardInfo ->
            binding.cardInfoText.text = cardInfo ?: ""
        }

        // Observe button state changes
        dailyViewModel.isDrawEnabled.observe(viewLifecycleOwner) { isEnabled ->
            binding.drawCardButton.isEnabled = isEnabled
            if (isEnabled) {
                binding.tarotCardView.showUndrawnState()
            }
        }

        // Observe card changes
        dailyViewModel.card.observe(viewLifecycleOwner) { card ->
            card?.let { binding.tarotCardView.displayCard(it) }
        }

        // Initial check for saved card
        dailyViewModel.checkSavedCard()

        return root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
        // Check card validity when resuming the app
        dailyViewModel.checkSavedCard()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}