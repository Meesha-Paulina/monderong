package cz.ludogry.textadventures.monderong.navigation

import android.content.Context
import android.view.MenuItem
import androidx.lifecycle.ViewModel
import cz.ludogry.textadventures.monderong.game.Item
import cz.ludogry.textadventures.monderong.game.MonderongRooms
import cz.ludogry.textadventures.monderong.game.Room

/**
 * This model mainly contains the current position and items.
 */
class RoomsViewModel : ViewModel(){

    var rooms = MonderongRooms()
    var items: HashSet<Item> = HashSet()

    var currentRoom : Room = Room()

    fun initRooms(context: Context) {
        currentRoom  = rooms.initRooms(context)
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

    fun useClicked(p0: MenuItem?, roomsFragment: RoomsFragment): Boolean {
        currentRoom.use(p0, roomsFragment, this)
        roomsFragment.roomChange()
        return true
    }

    fun pickClicked(p0: MenuItem?, roomsFragment: RoomsFragment): Boolean {
        currentRoom.pick(p0, roomsFragment, this)
        roomsFragment.roomChange()
        return true
    }

    fun pick(theItem: Item) {
        items.add(theItem)
        currentRoom.items.remove(theItem)
    }
}
