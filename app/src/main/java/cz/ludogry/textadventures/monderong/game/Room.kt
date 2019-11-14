package cz.ludogry.textadventures.monderong.game

class Room () {
    var north: Room? = null
    var west: Room? = null
    var south: Room? = null
    var east: Room? = null

    var roomName: String? = null
    var description: String? = null
    val items: ArrayList<Item> = arrayListOf()

//    fun useItem(item: Item, inventory: ArrayList<Item>): {
//        return null
//    }


}