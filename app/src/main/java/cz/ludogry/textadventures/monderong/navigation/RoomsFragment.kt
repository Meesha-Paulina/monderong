package cz.ludogry.textadventures.monderong.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import cz.ludogry.textadventures.monderong.R
import cz.ludogry.textadventures.monderong.databinding.RoomsFragmentBinding
import cz.ludogry.textadventures.monderong.game.Item

class RoomsFragment : Fragment() {
    val useListener = UseListener(this)
    val pickListener = PickListener(this)

    companion object {
        fun newInstance() = RoomsFragment()
    }

    // TODO private
    lateinit var viewModel: RoomsViewModel

    private lateinit var binding: RoomsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil
            .inflate(inflater, R.layout.rooms_fragment, container, false)


        return binding.root
    }

    fun hideDirections(binding: RoomsFragmentBinding) {
        binding.north.visibility = if (viewModel.north()) VISIBLE else INVISIBLE
        binding.east.visibility = if (viewModel.east()) VISIBLE else INVISIBLE
        binding.south.visibility = if (viewModel.south()) VISIBLE else INVISIBLE
        binding.west.visibility = if (viewModel.west()) VISIBLE else INVISIBLE
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RoomsViewModel::class.java)
        viewModel.initRooms(this.requireContext())

        roomChange()

        binding.north.setOnClickListener {
            viewModel.goNorth(this)
        }
        binding.east.setOnClickListener {
            viewModel.goEast(this)
        }
        binding.south.setOnClickListener {
            viewModel.goSouth(this)
        }
        binding.west.setOnClickListener {
            viewModel.goWest(this)
        }

    }

    private fun showUseMenu(button: View?) {
        val useMenu = PopupMenu(this.activity, button)

        useMenu.setOnMenuItemClickListener(this.useListener)
        useMenu.inflate(R.menu.use_menu)
        useMenu.menu.findItem(R.id.use_nothing).isVisible = viewModel.items.isEmpty()
        useMenu.menu.findItem(R.id.use_firelock).isVisible = viewModel.items.contains(Item.firelock)
        useMenu.menu.findItem(R.id.use_sword).isVisible = viewModel.items.contains(Item.sword)
        useMenu.menu.findItem(R.id.use_torch).isVisible = viewModel.items.contains(Item.torch)
        useMenu.menu.findItem(R.id.use_flaming_torch).isVisible = viewModel.items.contains(Item.flaming_torch)

        useMenu.show()
    }

    private fun showPickMenu(button: View?) {
        val pickMenu = PopupMenu(this.activity, button)
        pickMenu.setOnMenuItemClickListener(this.pickListener)
        pickMenu.inflate(R.menu.pick_menu)
        pickMenu.menu.findItem(R.id.pick_nothing).isVisible = viewModel.currentRoom.items.isEmpty()
        pickMenu.menu.findItem(R.id.pick_firelock).isVisible = viewModel.currentRoom.items.contains(Item.firelock)
        pickMenu.menu.findItem(R.id.pick_sword).isVisible = viewModel.currentRoom.items.contains(Item.sword)
        pickMenu.menu.findItem(R.id.pick_torch).isVisible = viewModel.currentRoom.items.contains(Item.torch)
        pickMenu.menu.findItem(R.id.pick_treasure).isVisible = viewModel.currentRoom.items.contains(Item.treasure)

        pickMenu.show()
    }

    fun onVictory() {
        findNavController().navigate(R.id.action_roomsFragment_to_victoryFragment)
    }

    fun onLoss() {
        findNavController().navigate(R.id.action_roomsFragment_to_gameOverFragment)
    }

    fun roomChange() {
        hideDirections(binding)
        binding.roomCaption.text = viewModel.currentRoom.roomName
        binding.roomDescription.text = viewModel.currentRoom.description
        binding.pick.setOnClickListener { button ->
            showPickMenu(button)
        }
        binding.use.setOnClickListener { button ->
            showUseMenu(button)
        }
    }
}

class UseListener (val fragment: RoomsFragment): PopupMenu.OnMenuItemClickListener {
    override fun onMenuItemClick(p0: MenuItem?): Boolean {
        fragment.viewModel.useClicked(p0, fragment)
        return true
    }

}

class PickListener (val fragment: RoomsFragment): PopupMenu.OnMenuItemClickListener {
    override fun onMenuItemClick(p0: MenuItem?): Boolean {
        fragment.viewModel.pickClicked(p0, fragment)
        return true
    }

}