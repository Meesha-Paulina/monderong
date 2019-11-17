package cz.ludogry.textadventures.monderong.navigation

import android.content.Context
import android.view.MenuItem
import androidx.lifecycle.ViewModel
import cz.ludogry.textadventures.monderong.R
import cz.ludogry.textadventures.monderong.game.Item
import cz.ludogry.textadventures.monderong.game.MonderongRooms
import cz.ludogry.textadventures.monderong.game.Room

class RoomsViewModel : ViewModel(){

    private var rooms = MonderongRooms()
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
        if (currentRoom.roomName == roomsFragment.getString(R.string.dragon)) {
                if (p0?.itemId == R.id.use_sword) {
                    currentRoom = rooms.killedDragon()
                    roomsFragment.showMessage(roomsFragment.getString(R.string.swordUsed))
                } else {
                    roomsFragment.onLoss()
                }
        } else if (p0?.itemId == R.id.use_flaming_torch
                   && currentRoom.roomName == roomsFragment.getString(R.string.caveEntrance)) {
            currentRoom.description=roomsFragment.getString(R.string.caveEntranceDescription2)
            currentRoom.north = rooms.getCave()
            roomsFragment.showMessage(roomsFragment.getString(R.string.flamingTorchUsed))
        } else if (p0?.itemId == R.id.use_firelock && items.contains(Item.torch)) {
            items.remove(Item.firelock)
            items.remove(Item.torch)
            items.add(Item.flaming_torch)
            roomsFragment.showMessage(roomsFragment.getString(R.string.firelockUsed))
        } else if (p0?.itemId != R.id.use_nothing){
            roomsFragment.showMessage(roomsFragment.getString(R.string.noUse))
        }
        roomsFragment.roomChange()
        return true
    }

    fun pickClicked(p0: MenuItem?, roomsFragment: RoomsFragment): Boolean {
        when {
            currentRoom.roomName == roomsFragment.getString(R.string.dragon) -> roomsFragment.onLoss()
            p0?.itemId == R.id.pick_treasure -> {
                roomsFragment.onVictory()
            }
            p0?.itemId == R.id.pick_torch -> {
                roomsFragment.showMessage(roomsFragment.getString(R.string.pickup) + roomsFragment.getString(R.string.torch))
                pick(Item.torch)
            }
            p0?.itemId == R.id.pick_sword -> {
                roomsFragment.showMessage(roomsFragment.getString(R.string.pickup) + roomsFragment.getString(R.string.sword))
                pick(Item.sword)
            }
            p0?.itemId == R.id.pick_firelock -> {
                roomsFragment.showMessage(roomsFragment.getString(R.string.pickup) + roomsFragment.getString(R.string.firelock))
                pick(Item.firelock)
            }
        }
        roomsFragment.roomChange()
        return true
    }

    private fun pick(theItem: Item) {
        items.add(theItem)
        currentRoom.items.remove(theItem)
    }
}
