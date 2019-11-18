package cz.ludogry.textadventures.monderong.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import cz.ludogry.textadventures.monderong.R
import cz.ludogry.textadventures.monderong.databinding.RoomsFragmentBinding
import cz.ludogry.textadventures.monderong.game.Item

/**
 * The most important fragment in this application. Shows current room - Name, description, buttons
 * for directions (north, east, west, south) and buttons for picking and using an item.
 */
class RoomsFragment : Fragment() {

    private lateinit var viewModel: RoomsViewModel

    private lateinit var binding: RoomsFragmentBinding

    /**
     * Initiates the binding and inflates the fragment.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil
            .inflate(inflater, R.layout.rooms_fragment, container, false)

        return binding.root
    }

    /**
     * Initiates the viewModel, changes to the starting room.
     */
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

    /**
     * Navigates to victory fragment.
     */
    fun onVictory() {
        findNavController().navigate(R.id.action_roomsFragment_to_victoryFragment)
    }

    /**
     * Navigates to game over fragment.
     */
    fun onLoss() {
        findNavController().navigate(R.id.action_roomsFragment_to_gameOverFragment)
    }

    /**
     * Changes the room - shows new room name, description, hides the directions and items
     * on the use and pick list.
     */
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

    /**
     * Shows toast message - used mainly for picking and using items.
     */
    fun showMessage(message: String) {
        Toast.makeText(
            this.requireContext(),
            message,
            Toast.LENGTH_LONG).show()

    }

    private fun hideDirections(binding: RoomsFragmentBinding) {
        binding.north.visibility = if (viewModel.north()) VISIBLE else INVISIBLE
        binding.east.visibility = if (viewModel.east()) VISIBLE else INVISIBLE
        binding.south.visibility = if (viewModel.south()) VISIBLE else INVISIBLE
        binding.west.visibility = if (viewModel.west()) VISIBLE else INVISIBLE
    }

    private fun showUseMenu(button: View?) {
        val useMenu = PopupMenu(this.activity, button)

        useMenu.setOnMenuItemClickListener{
            viewModel.useClicked(it, this)
        }
        useMenu.inflate(R.menu.use_menu)
        useMenu.menu.findItem(R.id.use_nothing).isVisible = viewModel.items.isEmpty()
        useMenu.menu.findItem(R.id.use_firelock).isVisible = viewModel.items.contains(Item.FIRELOCK)
        useMenu.menu.findItem(R.id.use_sword).isVisible = viewModel.items.contains(Item.SWORD)
        useMenu.menu.findItem(R.id.use_torch).isVisible = viewModel.items.contains(Item.TORCH)
        useMenu.menu.findItem(R.id.use_flaming_torch).isVisible = viewModel.items.contains(Item.FLAMING_TORCH)

        useMenu.show()
    }

    private fun showPickMenu(button: View?) {
        val pickMenu = PopupMenu(this.activity, button)
        pickMenu.setOnMenuItemClickListener{
            viewModel.pickClicked(it, this)
        }
        pickMenu.inflate(R.menu.pick_menu)
        pickMenu.menu.findItem(R.id.pick_nothing).isVisible = viewModel.currentRoom.items.isEmpty()
        pickMenu.menu.findItem(R.id.pick_firelock).isVisible = viewModel.currentRoom.items.contains(Item.FIRELOCK)
        pickMenu.menu.findItem(R.id.pick_sword).isVisible = viewModel.currentRoom.items.contains(Item.SWORD)
        pickMenu.menu.findItem(R.id.pick_torch).isVisible = viewModel.currentRoom.items.contains(Item.TORCH)
        pickMenu.menu.findItem(R.id.pick_treasure).isVisible = viewModel.currentRoom.items.contains(Item.TREASURE)

        pickMenu.show()
    }
}