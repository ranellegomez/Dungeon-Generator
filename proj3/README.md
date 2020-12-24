# BYOW Design Document

**Names**: Sherry Fan, Ranelle Gomez

## Classes and Data Structures
### [Engine]
**Fields**
* [WIDTH]: The width of this world.
* [HEIGHT]: The width of this world.
* [SAVED_DATA_FOLDER]: A File object with the director to the contents of the saved data.
* [KEYBOARD]: The integer 0 signifying the selection for keyboard input.
* [RANDOM]: The integer 1 signifying the selection of randomized input.
* [STRING]: The integer 2 signifying the selection of a string input.
* [ter]: A TERenderer object for this Engine object.

### [Game]
**Fields**
* [_r]: A Random object that represents the seed of this Game.
* [_world]: The 2D TETile array of this Game.
* [player]: The Position of the player of this Game.

### [Game]
**Fields**
* [_x]: The x-coordinate of this position object.
* [_y]: The y-coordinate of this position object.

### [Room]
**Fields**
* [_width]: An integer representing the width of this room object.
* [_height]: An integer the height of this room object.
* [_bottomLeft]: The Position object of the bottom-left corner of this room object.
* [_bottomRight]: The Position object of the bottom-right corner of this room object.
* [_center]: The Position object of the center of this room object.
* [_tile]: The TETile object of this Room object for aesthetic.

### [World]
**Fields**
* [tiles]: An 2D array of all TETile objects in this World.
* [rooms]: An ArrayList of Room objects in this World.
* [connections]: A HashMap of all connections from one Room object to another.

## Algorithms

### [Engine]
**Methods**
* [interactWithKeyboard]: Communicate with user-inputted commands on their keyboard. Handles all inputs, including from the main menu.
* [interactWithInputString]: Given a String INPUT, parse INPUT, instantiate a WorldGenerator, populate a TETile 2D array, and return the array.

### [Game]
**Methods**
* [inWorld]: Given two ints, X and Y, return true if they are in range of the _world.
* [startPlayerAtRandom]: Put the player at a random position within range of _world’s dimensions.
* [placePlayer]: Given two ints, X and Y, place the player at that Position.
* [play]: Given an inputSource INPUT, return the corresponding world.

### [Position]
**Methods**
* [getX]: Return the x-coordinate of this Position object.
* [getY]: Return the y-coordinate of this Position object.

### [Room]
**Methods**
* [getWidth]: Return this width.
* [getHeight]: Return this height.
* [getBottomLeftPosition]: Return this bottom-left position.
* [getTopRightPosition]: Return this top-right position.
* [getTopLeftPosition]: Return this top-left position.
* [getBottomRightPosition]: Return this bottom-right position.
* [getTile]: Return this tile aesthetic.
* [getCenter]: Return this center position.
* [getDegreeTo]: Given a Room S, return the degree from this Room object to S.
* [isInside]: Given a Position P, return true if p is inside this Room’s area; return false otherwise.
* [isOverlapping]: Given a Room R, return true if R overlaps with this room’s area; return false otherwise.
* [toString]: Return the x- and y-coordinates of the bottom-left and bottom-right corners of this room.

### [World]
**Methods**
* [squareDist]: Given two Positions, P1 and P2, return the squared distance between them as an int.
* [getTiles]: Return this’ tiles.
* [getRooms]: Return this’ rooms, an ArrayList.
* [getConnections]: Return a HashMap of this’ connections.
* [fillWorld]: Populate the 2D TETile array with NOTHING tiles.
* [addRoom]: Given a Room R, add it to this World.
* [overlapsWithAny]: Given a Room ROOM, return true if ROOM overlaps with any of this’ rooms; false otherwise. 
* [closest]: Given a Room ROOM, return the closest Room in this World instance.
* [connect]: Given two Rooms, R1 and R2, add R1 as a key to value R2 in the CONNECTIONS HashMap.
* [isConnected]: Given two Rooms, R1 and R2, return true if they are connected; false otherwise.


## Persistence
In order to persist the status of the game, we will need to ... To do this, ...

## Works cited
* Persistence: https://inst.eecs.berkeley.edu/~cs61b/sp20/materials/lab/lab12/index.html
* Software engineering: https://inst.eecs.berkeley.edu/~cs61b/sp20/materials/lab/lab13/index.html
