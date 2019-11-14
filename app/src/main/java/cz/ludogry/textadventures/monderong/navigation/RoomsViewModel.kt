package cz.ludogry.textadventures.monderong.navigation

import android.view.MenuItem
import androidx.lifecycle.ViewModel
import cz.ludogry.textadventures.monderong.R
import cz.ludogry.textadventures.monderong.game.Item
import cz.ludogry.textadventures.monderong.game.MonderongRooms
import cz.ludogry.textadventures.monderong.game.Room

class RoomsViewModel : ViewModel(){

    private var rooms = MonderongRooms()
    var items: HashSet<Item> = HashSet()

    var currentRoom : Room

    init {
       currentRoom  = rooms.initRooms()
    }

    fun north(): Boolean {
        return currentRoom.north != null
    }

    fun east(): Boolean {
        return currentRoom.east != null
    }

    fun south(): Boolean {
        return currentRoom.south != null
    }

    fun west(): Boolean {
        return currentRoom.west != null
    }

    fun goNorth(roomsFragment: RoomsFragment) {
        currentRoom = currentRoom.north!!
        roomsFragment.roomChange()
    }

    fun goEast(roomsFragment: RoomsFragment) {
        currentRoom = currentRoom.east!!
        roomsFragment.roomChange()
    }

    fun goSouth(roomsFragment: RoomsFragment) {
        currentRoom = currentRoom.south!!
        roomsFragment.roomChange()
    }

    fun goWest(roomsFragment: RoomsFragment) {
        currentRoom = currentRoom.west!!
        roomsFragment.roomChange()
    }

    fun itemClicked(p0: MenuItem?, roomsFragment: RoomsFragment) {
        if ((currentRoom.roomName == "Dragon!") && (p0?.itemId != R.id.use_sword)) {
            roomsFragment.onLoss()
        } else if (p0?.itemId == R.id.pick_treasure) {
            roomsFragment.onVictory()
        } else if (p0?.itemId == R.id.pick_torch) {
            pick(Item.torch)
        } else if (p0?.itemId == R.id.pick_sword) {
            pick(Item.sword)
        } else if (p0?.itemId == R.id.pick_firelock) {
            pick(Item.firelock)
        } else if (p0?.itemId == R.id.use_flaming_torch) {
            if (currentRoom.roomName == "Cave entrance"){
                currentRoom.description="This is the entrance to the legendary Great Cave of Monderong."
                currentRoom.north = rooms.getCave()
            }
        } else if (p0?.itemId == R.id.use_firelock) {
            if (items.contains(Item.torch)) {
                items.remove(Item.firelock)
                items.remove(Item.torch)
                items.add(Item.flaming_torch)
            }
        } else if (p0?.itemId == R.id.use_sword) {
            if (currentRoom.roomName == "Dragon!") {
                currentRoom.north?.south = rooms.killedDragon()
                currentRoom.south?.north = rooms.killedDragon()
                currentRoom = rooms.killedDragon()
            }
        }
        roomsFragment.roomChange()

    }

    private fun pick(theItem: Item) {
        items.add(theItem)
        currentRoom.items.remove(theItem)
    }
}
