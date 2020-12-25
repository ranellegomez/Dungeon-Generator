package byow.Core;

import byow.TileEngine.TETile;

import java.io.Serializable;

public class Room implements Serializable {
    /**
     * Prevents JVM from erroring when serializing.
     */
    private static final long serialVersionUID = 3786198910865385080L;
    private final int _width;
    private final int _height;
    private final Position _bottomLeft;
    private final Position _topRight;
    private final Position _topLeft;
    private final Position _bottomRight;
    private final Position _center;
    private final TETile _tile;

    /**
     * Construct a room object.
     */
    public Room(int width, int height, Position bottomLeft, TETile tile) {
        _width = width;
        _height = height;
        _bottomLeft = bottomLeft;
        _topRight = new Position(_bottomLeft.getX() + getWidth() - 1,
                                 _bottomLeft.getY() + getHeight() - 1);
        _topLeft = new Position(_bottomLeft.getX(),
                                _bottomLeft.getY() + getHeight() - 1);
        _bottomRight =
                new Position(_bottomLeft.getX() + getWidth() - 1,
                             _bottomLeft.getY());
        _center = (new Position(
                (getBottomRightPosition().getX() + getBottomLeftPosition()
                        .getX()) / 2,
                (getTopRightPosition().getY() + getBottomRightPosition().getY())
                        / 2));
        _tile = tile;
    }

    /**
     * Return the width of this room.
     */
    public int getWidth() {
        return _width;
    }

    /**
     * Return the height of this room.
     */
    public int getHeight() {
        return _height;
    }

    public Position getBottomLeftPosition() {
        return _bottomLeft;
    }

    public Position getTopRightPosition() {
        return _topRight;
    }

    public Position getTopLeftPosition() {
        return _topLeft;
    }

    public Position getBottomRightPosition() {
        return _bottomRight;
    }

    public TETile getTile() {
        return _tile;
    }

    public Position getCenter() {
        return _center;
    }

    /**
     * Return the direction of the connecting room S from this center
     * position to the one of S.
     */
    public double getDegreeTo(Room S) {
        double angleFromThisToS =
                Math.toDegrees(
                        Math.atan2(S.getCenter().getY() - getCenter().getY(),
                                   S.getCenter().getX() - getCenter().getX()));

        if (angleFromThisToS < 0) {
            angleFromThisToS += 360;
        }
        return angleFromThisToS;
    }

    /**
     * Returns if this room overlaps with another room
     */
    public boolean isOverlapping(Room r) {
        return !(r._topRight.getY() + 1 < _bottomLeft.getY() ||
                r._bottomLeft.getY() - 1 > _topRight.getY() ||
                r._bottomLeft.getX() - 1 > _topRight.getX() ||
                r._topRight.getX() + 1 < _bottomLeft.getX());
    }

    @Override
    public String toString() {
        return "(" + _bottomLeft.getX() + ", " + _bottomLeft.getY() + ") | " +
                "(" + _topRight.getX() + ", " + _topRight.getY() + ")";
    }

    /**
     * Returns the location of the given exit of this room.
     */
    public Position exitLocation(String exit) {
        return switch (exit) {
            case "EAST" -> new Position(_topRight.getX(), _center.getY());
            case "NORTH" -> new Position(_center.getX(), _topRight.getY());
            case "SOUTH" -> new Position(_center.getX(), _bottomRight.getY());
            default -> new Position(_bottomLeft.getX(), _center.getY());
        };
    }
}
