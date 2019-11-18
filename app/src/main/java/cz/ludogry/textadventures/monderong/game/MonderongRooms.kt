package cz.ludogry.textadventures.monderong.game

import android.content.Context
import cz.ludogry.textadventures.monderong.R

/**
 * This class contains all rooms, their descriptions, paths and items in them.
 */
class MonderongRooms {
    var importantRooms = HashMap<Int, Room>()

    /**
     * Initializes all the rooms and returns the initial room.
     * @param context - android context for reading string resources.
     */
    fun initRooms(context: Context): Room {

        // rooms named by their position on an xy map
        val c2start = Room()
        val a5=Room()
        val b3=CaveEntrance()
        val b4=Room()
        val b5=Room()

        val b6=DragonRoom()             // room with dragon
        val b62=Room()            // room with killed dragon

        val b7=Room()
        val b8=Room()
        val c3=Room()
        val c5=Room()
        val c7=Room()
        val c8=Room()
        val d1=Room()
        val d2=Room()
        val d3=Room()

        c2start.roomName = context.getString(R.string.forest)
        c2start.description = context.getString(R.string.startDescription)
        c2start.north = c3
        c2start.east = d2

        a5.roomName = context.getString(R.string.uninterestingPlace)
        a5.description = context.getString(R.string.uninterestingDescription)
        a5.east = b5

        b3.roomName = context.getString(R.string.caveEntrance)
        b3.description = context.getString(R.string.entranceDescription)
        b3.east=c3

        b4.roomName = context.getString(R.string.cave)
        b4.description = context.getString(R.string.firstCaveDescription)
        b4.south=b3
        b4.north=b5
        importantRooms[R.string.cave] = b4

        b5.roomName = context.getString(R.string.crossroad)
        b5.description = context.getString(R.string.crossroadDescription)
        b5.east=c5
        b5.west=a5
        b5.north=b6
        b5.south=b4

        b6.roomName = context.getString(R.string.dragon)
        b6.description = context.getString(R.string.dragonDescription)

        b62.roomName = context.getString(R.string.deadDragon)
        b62.description = context.getString(R.string.deadDragonDescription)
        b62.north=b7
        b62.south=b5
        importantRooms[R.string.deadDragon] = b62

        b7.roomName = context.getString(R.string.stinkyCave)
        b7.description = context.getString(R.string.stinkyCaveDescription)
        b7.north=b8
        b7.south=b6

        b8.roomName = context.getString(R.string.cave)
        b8.description= context.getString(R.string.closeCaveDescription)
        b8.south=b7
        b8.east=c8

        c3.roomName = context.getString(R.string.forest)
        c3.description = context.getString(R.string.anotherForestDescription)
        c3.west=b3
        c3.east=d3
        c3.south=c2start
        c3.items.add(Item.FIRELOCK)

        c5.roomName = context.getString(R.string.armory)
        c5.description = context.getString(R.string.armoryDescription)
        c5.west=b5
        c5.items.add(Item.SWORD)

        c7.roomName = context.getString(R.string.treasureRoom)
        c7.description = context.getString(R.string.treasureDescription)
        c7.north=c8
        c7.items.add(Item.TREASURE)

        c8.roomName = context.getString(R.string.cave)
        c8.description = context.getString(R.string.caveVictoryDescription)
        c8.west=b8
        c8.south=c7

        d1.roomName = context.getString(R.string.meadow)
        d1.description = context.getString(R.string.meadowDescription)
        d1.north=d2
        d1.items.add(Item.TORCH)

        d2.roomName = context.getString(R.string.forest)
        d2.description = context.getString(R.string.lostForestDescription)
        d2.west=c2start
        d2.north=d3
        d2.south=d1

        d3.roomName = context.getString(R.string.forest)
        d3.description = context.getString(R.string.scaryForestDescription)
        d3.south=d2
        d3.west=c3

        return c2start
    }

    /**
     * This returns the first room in the cave.
     */
    fun openCave(): Room {
        return importantRooms[R.string.cave]!!
    }

    /**
     * This returns the room with killed dragon and also connects the adjacent rooms to it.
     */
    fun killedDragon(): Room {
        val killedDragonRoom = importantRooms[R.string.deadDragon]!!
        killedDragonRoom.south?.north = killedDragonRoom
        killedDragonRoom.north?.south = killedDragonRoom
        return killedDragonRoom
    }

}