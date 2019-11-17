package cz.ludogry.textadventures.monderong.game

open class Room {

    var north: Room? = null
    var west: Room? = null
    var south: Room? = null
    var east: Room? = null

    var roomName: String? = null
    var description: String? = null
    val items: ArrayList<Item> = arrayListOf()

}