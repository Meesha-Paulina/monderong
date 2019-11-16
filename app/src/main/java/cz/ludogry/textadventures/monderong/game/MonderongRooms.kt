package cz.ludogry.textadventures.monderong.game

import android.content.Context
import cz.ludogry.textadventures.monderong.R

class MonderongRooms {
    var rooms = arrayListOf<Room>()
    fun initRooms(context: Context): Room {

        val start = Room()
        val a5=Room()
        val b3=Room()
        val b4=Room()
        val b5=Room()

        val b6=Room()             // room with dragon
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
        start.roomName = context.getString(R.string.forest)
        start.description = context.getString(R.string.startDescription)
        start.north = c3
        start.east = d2

        a5.roomName = context.getString(R.string.uninterestingPlace)
        a5.description = context.getString(R.string.uninterestingDescription)
        a5.east = b5

        b3.roomName = context.getString(R.string.caveEntrance)
        b3.description= context.getString(R.string.entranceDescription)
        b3.east=c3

        b4.roomName = context.getString(R.string.cave)
        b4.description = context.getString(R.string.firstCaveDescription)
        b4.south=b3
        b4.north=b5

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
        c3.south=start
        c3.items.add(Item.firelock)

        c5.roomName = context.getString(R.string.armory)
        c5.description = context.getString(R.string.armoryDescription)
        c5.west=b5
        c5.items.add(Item.sword)

        c7.roomName = context.getString(R.string.treasureRoom)
        c7.description = context.getString(R.string.treasureDescription)
        c7.north=c8
        c7.items.add(Item.treasure)

        c8.roomName = context.getString(R.string.cave)
        c8.description = context.getString(R.string.caveVictoryDescription)
        c8.west=b8
        c8.south=c7

        d1.roomName = context.getString(R.string.meadow)
        d1.description = context.getString(R.string.meadowDescription)
        d1.north=d2
        d1.items.add(Item.torch)

        d2.roomName = context.getString(R.string.forest)
        d2.description = context.getString(R.string.lostForestDescription)
        d2.west=start
        d2.north=d3
        d2.south=d1

        d3.roomName = context.getString(R.string.forest)
        d3.description = context.getString(R.string.scaryForestDescription)
        d3.south=d2
        d3.west=c3

        rooms.add(start)
        rooms.add(a5)
        rooms.add(b3)
        rooms.add(b4)
        rooms.add(b5)

        rooms.add(b6)            // room with dragon
        rooms.add(b62)           // room with killed dragon

        rooms.add(b7)
        rooms.add(b8)
        rooms.add(c3)
        rooms.add(c5)
        rooms.add(c7)
        rooms.add(c8)
        rooms.add(d1)
        rooms.add(d2)
        rooms.add(d3)

        return start
    }

    fun getCave(): Room {
        return rooms[3]
    }

    fun killedDragon(): Room {
        val killedDragonRoom = rooms[6]
        killedDragonRoom.south?.north = killedDragonRoom
        killedDragonRoom.north?.south = killedDragonRoom
        return killedDragonRoom
    }


}