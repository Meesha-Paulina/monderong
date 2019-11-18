package cz.ludogry.textadventures.monderong.game

import android.view.MenuItem
import cz.ludogry.textadventures.monderong.R
import cz.ludogry.textadventures.monderong.navigation.RoomsFragment
import cz.ludogry.textadventures.monderong.navigation.RoomsViewModel

/**
 * This is base room class. It contains references to adjacent rooms, room name, description and items.
 * It also handles pick and use commands.
 */
open class Room {

    var north: Room? = null
    var west: Room? = null
    var south: Room? = null
    var east: Room? = null

    var roomName: String? = null
    var description: String? = null
    val items: ArrayList<Item> = arrayListOf()

    /**
     * Handles picking up an item.
     */
    open fun pick(p0: MenuItem?, fragment: RoomsFragment, model: RoomsViewModel) {
        when {
            p0?.itemId == R.id.pick_treasure -> {
                fragment.onVictory()
            }
            p0?.itemId == R.id.pick_torch -> {
                fragment.showMessage(fragment.getString(R.string.pickup) + fragment.getString(
                    R.string.torch))
                model.pick(Item.TORCH)
            }
            p0?.itemId == R.id.pick_sword -> {
                fragment.showMessage(fragment.getString(R.string.pickup) + fragment.getString(
                    R.string.sword))
                model.pick(Item.SWORD)
            }
            p0?.itemId == R.id.pick_firelock -> {
                fragment.showMessage(fragment.getString(R.string.pickup) + fragment.getString(
                    R.string.firelock))
                model.pick(Item.FIRELOCK)
            }
        }
    }

    /**
     * Handles using an item.
     */
    open fun use(p0: MenuItem?, fragment: RoomsFragment, model: RoomsViewModel) {
        if (p0?.itemId == R.id.use_firelock && model.items.contains(Item.TORCH)) {
            model.items.remove(Item.FIRELOCK)
            model.items.remove(Item.TORCH)
            model.items.add(Item.FLAMING_TORCH)
            fragment.showMessage(fragment.getString(R.string.firelockUsed))
        } else if (p0?.itemId != R.id.use_nothing){
            fragment.showMessage(fragment.getString(R.string.noUse))
        }
    }

}

/**
 * Room with dragon. It has special use action, so that you can kill the dragon.
 * If you try to pick anything or use something else than a sword, you lose the game.
 */
class DragonRoom : Room() {
    override fun use(p0: MenuItem?, fragment: RoomsFragment, model: RoomsViewModel) {
        if (p0?.itemId == R.id.use_sword) {
            model.currentRoom = model.rooms.killedDragon()
            fragment.showMessage(fragment.getString(R.string.swordUsed))
        } else {
            fragment.onLoss()
        }
    }

    override fun pick(p0: MenuItem?, fragment: RoomsFragment, model: RoomsViewModel) {
        fragment.onLoss()
    }
}

/**
 * Entry to the cave. If you use flaming torch, you get to the cave.
 */
class CaveEntrance : Room() {
    override fun use(p0: MenuItem?, fragment: RoomsFragment, model: RoomsViewModel) {
        if (p0?.itemId == R.id.use_flaming_torch) {
            description = fragment.getString(R.string.caveEntranceDescription2)
            north = model.rooms.openCave()
            fragment.showMessage(fragment.getString(R.string.flamingTorchUsed))
        } else {
            super.use(p0, fragment, model)
        }
    }
}