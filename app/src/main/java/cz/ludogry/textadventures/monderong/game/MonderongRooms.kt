package cz.ludogry.textadventures.monderong.game

class MonderongRooms {
    var rooms = arrayListOf<Room>()
    fun initRooms(): Room {

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
        start.roomName = "Forest"
        start.description = "It's a very dark night. You are in the middle of a forest."
        start.north = c3
        start.east = d2

        a5.roomName = "Uninteresting place"
        a5.description = "This is totally uninteresting place."
        a5.east = b5

        b3.roomName = "Cave entrance"
        b3.description="This is the entrance to the legendary Great Cave of Monderong. You are scared to go in without a light."
        b3.east=c3

        b4.roomName = "Cave"
        b4.description="The cave does not look quite as interesting as you imagined."
        b4.south=b3
        b4.north=b5

        b5.roomName = "Crossroad"
        b5.description="You find yourself on a crossroad. Strange smell and noise comes from a hall in the north."
        b5.east=c5
        b5.west=a5
        b5.north=b6
        b5.south=b4

        b6.roomName = "Dragon!"
        b6.description="Prepare for a battle, the dragon noticed you and wants to fight!"

        b62.roomName = "Dead dragon"
        b62.description="The body of the dragon lies on the hall's floor."
        b62.north=b7
        b62.south=b5

        b7.roomName = "Stinky cave"
        b7.description="The awful smell of the dead dragon reminds you of the glorious battle."
        b7.north=b8
        b7.south=b6

        b8.roomName = "Cave"
        b8.description="You are getting closer to the mystical treasure of Monderong."
        b8.south=b7
        b8.east=c8

        c3.roomName = "Forest"
        c3.description="Forest. A tree. And another. What would you expect, it's a forest."
        c3.west=b3
        c3.east=d3
        c3.south=start
        c3.items.add(Item.firelock)

        c5.roomName = "Armory"
        c5.description="Small room with a bunch of weapons."
        c5.west=b5
        c5.items.add(Item.sword)

        c7.roomName = "Treasure room"
        c7.description="Finally! There is the ancient treasure, let's get it to a museum."
        c7.north=c8
        c7.items.add(Item.treasure)

        c8.roomName = "Cave"
        c8.description="You can already feel the sensation of victory."
        c8.west=b8
        c8.south=c7

        d1.roomName = "Meadow"
        d1.description="Small meadow."
        d1.north=d2
        d1.items.add(Item.torch)

        d2.roomName = "Forest"
        d2.description="You are kinda lost in this forest, aren't you?"
        d2.west=start
        d2.north=d3
        d2.south=d1

        d3.roomName = "Forest"
        d3.description="This forest is scary."
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
        return rooms[6]
    }


}