package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.awt.*;
import java.util.Random;

public class WorldGenerator {
    final World _world;
    private final int _height;
    private final int _width;
    private final Random _r;
    private final long _seed;

    public WorldGenerator(int w, int h, long seed) {
        _height = h;
        _width = w;
        _world = new World(w, h);
        _r = new Random(seed);
        _seed = seed;
    }

    /**
     * Returns a random integer in the range [LOWER, UPPER)
     */
    private static int randInRange(int lower, int upper, Random r) {
        return r.nextInt(upper - lower) + lower;
    }

    /**
     * Because blue is pretty
     */
    static TETile randBlueTile(Random r) {
        int blue = randInRange(100, 255, r);
        int green = randInRange(0, 80, r);
        int red = randInRange(0, 40, r);
        int ch = randInRange(0, 65535, r);
        Color col = new Color(red, green, blue);
        return new TETile((char) ch, col, Color.BLACK, "the floor");
    }

    public World getWorld() {
        return _world;
    }

    public int getHeight() {
        return _height;
    }

    public int getWidth() {
        return _width;
    }

    public long getSeed() {
        return _seed;
    }

    /**
     * Helper method for room drawing. Returns the correct tile (wall or
     * non-wall) to be placed.
     */
    private TETile wallOrFloor(Room r, Position p) {
        int left = r.getBottomLeftPosition().getX();
        int right = r.getTopRightPosition().getX();
        int bottom = r.getBottomLeftPosition().getY();
        int top = r.getTopRightPosition().getY();
        if (p.getX() == left || p.getX() == right
                || p.getY() == top || p.getY() == bottom) {
            return Tileset.WALL;
        }
        return r.getTile();
    }

    /**
     * Draws a single room
     */
    private void drawRoom(Room r) {
        int initX = r.getBottomLeftPosition().getX();
        int initY = r.getBottomRightPosition().getY();
        for (int i = 0; i < r.getWidth(); i++) {
            for (int j = 0; j < r.getHeight(); j++) {
                Position currP = new Position(initX + i, initY + j);
                _world._tiles[initX + i][initY + j] = wallOrFloor(r, currP);
            }
        }
    }

    /**
     * Generates a random Room object
     */
    private Room generateRoom() {
        int roomX = randInRange(1, _width, _r);
        int roomY = randInRange(1, _height, _r);
        int roomWidth = randInRange(4, 20, _r);
        int roomHeight = randInRange(4, 15, _r);
        Room room = new Room(roomWidth, roomHeight, new Position(roomX, roomY),
                             randBlueTile(_r));
        return room;
    }

    /**
     * Checks that the room is non-overlapping with other rooms and also has
     * valid dimensions
     */
    private boolean validateRoom(Room r) {
        return r.getTopLeftPosition().getY() < _height
                && r.getBottomRightPosition().getX() < _width
                && !_world.overlapsWithAny(r);
    }

    private void drawAllRooms() {
        int numRooms = randInRange(3, 100, _r);
        for (int i = 0; i < numRooms; i++) {
            Room candidate = generateRoom();
            if (validateRoom(candidate)) {
                drawRoom(candidate);
                _world.addRoom(candidate);
            }
        }
    }

    /**
     * Draws a vertical hallway
     */
    private void verticalHall(int startY, int endY, int x, TETile tile) {
        for (int y = startY; y <= endY; y++) {
            drawTileSafe(x - 1, y, Tileset.WALL);
            _world._tiles[x][y] = tile;
            drawTileSafe(x + 1, y, Tileset.WALL);
        }
    }

    /**
     * Draws a horizontal hallway
     */
    private void horizontalHall(int startX, int endX, int y, TETile tile) {
        for (int x = startX; x <= endX; x++) {
            drawTileSafe(x, y - 1, Tileset.WALL);
            _world._tiles[x][y] = tile;
            drawTileSafe(x, y + 1, Tileset.WALL);
        }
    }

    /**
     * Draws a tile without overriding other tiles.
     */
    private void drawTileSafe(int x, int y, TETile tile) {
        if (_world._tiles[x][y].equals(Tileset.NOTHING)) {
            _world._tiles[x][y] = tile;
        }
    }

    /**
     * Given a double DEGREES, return the String of door bearings that
     * connect two rooms.
     */
    String[] getHallwayDoors(double degrees) {
        if (degrees == 0) {
            return new String[]{"EAST", "WEST"};
        } else if (degrees > 0 && degrees <= 45) {
            return new String[]{"EAST", "SOUTH"};
        } else if (degrees > 45 && degrees < 90) {
            return new String[]{"NORTH", "WEST"};
        } else if (degrees == 90) {
            return new String[]{"NORTH", "SOUTH"};
        } else if (degrees > 90 && degrees <= 135) {
            return new String[]{"NORTH", "EAST"};
        } else if (degrees > 135 && degrees < 180) {
            return new String[]{"WEST", "SOUTH"};
        } else if (degrees == 180) {
            return new String[]{"WEST", "EAST"};
        } else if (degrees > 180 && degrees <= 225) {
            return new String[]{"WEST", "NORTH"};
        } else if (degrees > 225 && degrees < 270) {
            return new String[]{"SOUTH", "EAST"};
        } else if (degrees == 270) {
            return new String[]{"SOUTH", "NORTH"};
        } else if (degrees > 270 && degrees <= 315) {
            return new String[]{"SOUTH", "WEST"};
        }
        return new String[]{"EAST", "WEST"};
    }

    /**
     * Connects two positions with hallways.
     */
    void connect(Position p1, Position p2, TETile tile) {
        int startY = Math.min(p1.getY(), p2.getY());
        int endY = Math.max(p1.getY(), p2.getY());
        int startX = Math.min(p1.getX(), p2.getX());
        int endX = Math.max(p1.getX(), p2.getX());
        horizontalHall(startX, endX, p1.getY(), tile);
        verticalHall(startY, endY, p2.getX(), tile);
    }

    private void drawHallways() {
        for (Room r : _world._rooms) {
            Room neighbor = _world.closest(r);
            if (!_world.isConnected(r, neighbor)) {
                double bearing = r.getDegreeTo(neighbor);
                String[] exits = getHallwayDoors(bearing);
                Position exit1 = r.exitLocation(exits[0]);
                Position exit2 = neighbor.exitLocation(exits[1]);
                connect(exit1, exit2, r.getTile());
                _world.connect(r, neighbor);
            }
        }
    }

    public World generateWorld() {
        drawAllRooms();
        drawHallways();
        return _world;
    }
}
