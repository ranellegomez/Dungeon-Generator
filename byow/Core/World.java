package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.HashMap;

public class World {
    TETile[][] _tiles;
    ArrayList<Room> _rooms = new ArrayList<>();
    HashMap<Room, Room> _connections = new HashMap<>();

    public World(int width, int height) {
        _tiles = new TETile[width][height];
        fillWorld();
    }

    private static int squaredDist(Position p1, Position p2) {
        int xDist = p1.getX() * p1.getX() - p2.getX() * p2.getX();
        int yDist = p1.getY() * p1.getY() - p2.getY() * p2.getY();
        return xDist + yDist;
    }

    public TETile getTile(int x, int y) {
        return _tiles[x][y];

    }

    public TETile[][] get_tiles() {
        return _tiles;
    }

    public ArrayList<Room> get_rooms() {
        return _rooms;
    }

    public HashMap<Room, Room> get_connections() {
        return _connections;
    }

    private void fillWorld() {
        for (int i = 0; i < _tiles.length; i++) {
            for (int j = 0; j < _tiles[0].length; j++) {
                _tiles[i][j] = Tileset.NOTHING;
            }
        }
    }

    public void addRoom(Room r) {
        _rooms.add(r);
    }

    /**
     * Returns if this a given room overlaps with any of the current world's
     * rooms.
     */
    public boolean overlapsWithAny(Room room) {
        for (Room r : _rooms) {
            if (r.isOverlapping(room)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the closest room to this room
     */
    public Room closest(Room room) {
        Room closest = null;
        int bestDist = Integer.MAX_VALUE;
        for (Room r : _rooms) {
            int newDist = squaredDist(r.getCenter(), room.getCenter());
            if (r != room && newDist < bestDist) {
                bestDist = newDist;
                closest = r;
            }
        }
        return closest;
    }

    public void connect(Room r1, Room r2) {
        _connections.put(r1, r2);
    }

    public boolean isConnected(Room r1, Room r2) {
        return _connections.get(r1) == r2 || _connections.get(r2) == r1;
    }

    public int getHeight() {
        return _tiles[0].length;
    }

    public int getWidth() {
        return _tiles.length;
    }
}
