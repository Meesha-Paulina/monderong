package cz.ludogry.textadventures.monderong.game

import android.view.MenuItem
import cz.ludogry.textadventures.monderong.R
import cz.ludogry.textadventures.monderong.navigation.RoomsFragment
import cz.ludogry.textadventures.monderong.navigation.RoomsViewModel

open class Room {

    var north: Room? = null
    var west: Room? = null
    var south: Room? = null
    var east: Room? = null

    var roomName: String? = null
    var description: String? = null
    val items: ArrayList<Item> = arrayListOf()

    open fun pick(p0: MenuItem?, fragment: RoomsFragment, model: RoomsViewModel) {
        when {
            p0?.itemId == R.id.pick_treasure -> {
                fragment.onVictory()
            }
            p0?.itemId == R.id.pick_torch -> {
                fragment.showMessage(fragment.getString(R.string.pickup) + fragment.getString(
                    R.string.torch))
                model.pick(Item.torch)
            }
            p0?.itemId == R.id.pick_sword -> {
                fragment.showMessage(fragment.getString(R.string.pickup) + fragment.getString(
                    R.string.sword))
                model.pick(Item.sword)
            }
            p0?.itemId == R.id.pick_firelock -> {
                fragment.showMessage(fragment.getString(R.string.pickup) + fragment.getString(
                    R.string.firelock))
                model.pick(Item.firelock)
            }
        }
    }

    open fun use(p0: MenuItem?, fragment: RoomsFragment, model: RoomsViewModel) {
        if (p0?.itemId == R.id.use_firelock && model.items.contains(Item.torch)) {
            model.items.remove(Item.firelock)
            model.items.remove(Item.torch)
            model.items.add(Item.flaming_torch)
            fragment.showMessage(fragment.getString(R.string.firelockUsed))
        } else if (p0?.itemId != R.id.use_nothing){
            fragment.showMessage(fragment.getString(R.string.noUse))
        }
    }

}

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

class CaveEntrance : Room() {
    override fun use(p0: MenuItem?, fragment: RoomsFragment, model: RoomsViewModel) {
        if (p0?.itemId == R.id.use_flaming_torch) {
            description = fragment.getString(R.string.caveEntranceDescription2)
            north = model.rooms.getCave()
            fragment.showMessage(fragment.getString(R.string.flamingTorchUsed))
        } else {
            super.use(p0, fragment, model)
        }
    }
}