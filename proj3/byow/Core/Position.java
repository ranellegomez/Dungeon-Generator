package byow.Core;

public class Position {
    private final int _x;
    private final int _y;

    /**
     * Construct a Position object.
     */
    public Position(int x, int y) {
        _x = x;
        _y = y;
    }

    public Position(Position p, int xOffSet, int yOffSet) {
        _x = p.getX() + xOffSet;
        _y = p.getY() + yOffSet;
    }

    public int getX() {
        return _x;
    }

    public int getY() {
        return _y;
    }

    @Override
    public String toString() {
        return "(" + _x + ", " + _y + ")";
    }
}
