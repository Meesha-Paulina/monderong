package cz.ludogry.textadventures.monderong.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import cz.ludogry.textadventures.monderong.R
import cz.ludogry.textadventures.monderong.databinding.MainMenuFragmentBinding

/**
 * Fragment with main menu. Contains buttons for game start, help and exit.
 */
class MainMenuFragment : Fragment() {

    /**
     * Initiates the binding and inflates the fragment.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: MainMenuFragmentBinding = DataBindingUtil
            .inflate(inflater, R.layout.main_menu_fragment, container, false)

        binding.startButton.setOnClickListener (
            Navigation.createNavigateOnClickListener(R.id.action_mainMenuFragment_to_roomsFragment)
        )
        binding.buttonHelp.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_mainMenuFragment_to_helpFragment)
        )
        binding.buttonExit.setOnClickListener {
            activity?.finish()
        }
        return binding.root
    }


}
