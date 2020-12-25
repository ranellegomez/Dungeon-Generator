package byow.Core;

import byow.TileEngine.TETile;

public class Player {
    private final TETile _tile;
    int _fightsWon;
    private Position _position;
    private int _health;
    private int _charisma;

    public Player(TETile tile) {
        _tile = tile;
        _health = 60;
        _charisma = 20;
        _fightsWon = 0;
    }

    public Player(TETile tile, int health, int charisma, int fightsWon) {
        _tile = tile;
        _health = health;
        _charisma = charisma;
        _fightsWon = fightsWon;
    }

    /**
     * Return this Player's _position attribute.
     */
    public Position getPosition() {
        return _position;
    }

    /**
     * Given a Position P, set _position to P.
     */
    public void setPosition(Position p) {
        _position = p;
    }

    public TETile getTile() {
        return _tile;
    }

    public int getHealth() {
        return _health;
    }

    public void setHealth(int h) {
        _health = h;
    }

    public int getCharisma() {
        return _charisma;
    }

    public void gainCharisma(int amount) {
        _charisma += amount;
    }

    public int getFightsWon() {
        return _fightsWon;
    }

    public void wonFight() {
        _fightsWon += 1;
    }
}

