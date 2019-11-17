package cz.ludogry.textadventures.monderong.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import cz.ludogry.textadventures.monderong.R
import cz.ludogry.textadventures.monderong.databinding.FragmentVictoryBinding

class VictoryFragment : Fragment() {

    private lateinit var binding: FragmentVictoryBinding

        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_victory, container, false)

        binding.backToMenu.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_victoryFragment_to_mainMenuFragment)
        )

        return binding.root
    }

}
