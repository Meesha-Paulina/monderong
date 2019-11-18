package cz.ludogry.textadventures.monderong


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import cz.ludogry.textadventures.monderong.databinding.FragmentHelpBinding

/**
 * A simple [Fragment] subclass.
 */
class HelpFragment : Fragment() {

    private lateinit var binding: FragmentHelpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_help, container, false)

        binding.helpBackToMenu.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_helpFragment_to_mainMenuFragment)
        )

        return binding.root
    }


}
