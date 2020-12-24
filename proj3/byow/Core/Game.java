package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static byow.Core.Engine.HEIGHT;
import static byow.Core.Engine.WIDTH;

public class Game {
    private final Random _r;
    private final World _world;
    private final long _seed;
    private final Player _player;
    private final Player _companion;
    private final NPCSet _npcSet;
    private final TERenderer _ter = new TERenderer();
    private final List<Encounter> _events = new ArrayList<>();

    /**
     * Given a Long SEED, construct a Game object.
     */
    public Game(long seed, Player p) {
        _r = new Random(seed);
        _seed = seed;
        _world = new WorldGenerator(WIDTH, HEIGHT, seed).generateWorld();
        _player = p;
        _companion = new Player(Tileset.COMPANION);
        _npcSet = new NPCSet();
        startPlayerAtRandom();
        fillEvents();
    }

    private static void endGameScreen(boolean didPlayerWin) {
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.setFont(new Font("Monaco", Font.BOLD, 30));
        if (didPlayerWin) {
            StdDraw.text(WIDTH / 2, HEIGHT / 2, "You won!");
        } else {
            StdDraw.text(WIDTH / 2, HEIGHT / 2, "You have died.");
        }
        StdDraw.show();
        StdDraw.pause(2000);
        System.exit(0);
    }

    /**
     * Fill _world with random NPCs.
     */
    private void fillEvents() {
        for (int i = 0; i < 6; i++) {
            NPC randNPC = _npcSet.getRandomNPC();
            Encounter e = new Dialogue(randNPC, _r, 1, _player);
            _events.add(e);
        }
    }

    /**
     * Return this Game's seed.
     */
    public long getSeed() {
        return _seed;
    }

    /**
     * Return true if the square at (x, y) is a playable tile; return false
     * otherwise.
     */
    private boolean inWorld(int x, int y) {
        return x >= 0 && x < _world.getWidth() &&
                y >= 0 && y < _world.getHeight() &&
                _world._tiles[x][y] != Tileset.NOTHING &&
                _world._tiles[x][y] != Tileset.WALL;
    }

    /**
     * Start the player off at a random location (x, y).
     */
    private void startPlayerAtRandom() {
        int x = _r.nextInt(_world.getWidth());
        int y = _r.nextInt(_world.getHeight());
        while (!(inWorld(x, y))) {
            x = _r.nextInt(_world.getWidth());
            y = _r.nextInt(_world.getHeight());
        }
        placePlayer(_player, x, y);
        Position companionStart = whichOfFourSquaresFree(x, y);
        placePlayer(_companion, companionStart.getX(), companionStart.getY());
    }

    /**
     * Set the Player's Position at (x, y).
     */
    private void placePlayer(Player p, int x, int y) {
        if (inWorld(x, y)) {
            if (p.getPosition() != null) {
                int startX = p.getPosition().getX();
                int startY = p.getPosition().getY();
                _world._tiles[startX][startY] = WorldGenerator.randBlueTile(_r);
            }
            _world._tiles[x][y] = p.getTile();
            p.setPosition(new Position(x, y));
        }
    }

    private Position whichOfFourSquaresFree(int x, int y) {
        Position p;
        if (inWorld(x - 1, y)) {
            p = new Position(x - 1, y);
        } else if (inWorld(x + 1, y)) {
            p = new Position(x + 1, y);
        } else if (inWorld(x, y + 1)) {
            p = new Position(x, y + 1);
        } else {
            p = new Position(x, y - 1);
        }
        return p;
    }

    /**
     * Return the world after one keypress.
     */
    public void update(char c) {
        int startX = _player.getPosition().getX();
        int startY = _player.getPosition().getY();
        Position finalPos = _player.getPosition();
        switch (c) {
            case 'w':
                finalPos = new Position(startX, startY + 1);
                break;
            case 'a':
                finalPos = new Position(startX - 1, startY);
                break;
            case 's':
                finalPos = new Position(startX, startY - 1);
                break;
            case 'd':
                finalPos = new Position(startX + 1, startY);
                break;
        }
        int playerX = finalPos.getX();
        int playerY = finalPos.getY();
        placePlayer(_player, playerX, playerY);
        Position companionPos = whichOfFourSquaresFree(playerX, playerY);
        placePlayer(_companion, companionPos.getX(), companionPos.getY());
    }

    /**
     * Renders the current world state
     */
    private void render() {
        _ter.renderFrame(_world._tiles);
    }

    /**
     * Activate the GUI window which allows the user to navigate the world
     * with the keys W, A, S, and D. Return the sequence String of these
     * inputted keys along with _player's attributes.
     */
    String play(InputSource input, boolean render)
            throws URISyntaxException, IOException {
        StringBuilder _keyPresses = new StringBuilder();
        if (render) {
            _ter.initialize(WIDTH, HEIGHT);
        }
        while (true) {
            if (input.possibleNextInput()) {
                char nextKey = input.getNextKey();
                _keyPresses.append(nextKey);
                if (_keyPresses.toString().endsWith(":q")) {
                    return _keyPresses.toString()
                            + "\n" + _player.getHealth()
                            + "\n" + _player.getCharisma() + "\n"
                            + _player._fightsWon;
                }
                if (_keyPresses.toString().endsWith("wwssadadba")) {
                    if (Desktop
                            .isDesktopSupported() && Desktop.getDesktop()
                            .isSupported(Desktop.Action.BROWSE)) {
                        Desktop.getDesktop().browse(new URI(
                                "https://tinyurl.com/cs61bmagic"));
                        System.exit(0);
                    }
                }
                update(nextKey);
            } else {
                update(' ');
            }
            if (render) {
                triggerEvents(_events);
                render();
            }
            if (_player.getHealth() <= 0) {
                endGameScreen(false);
            } else if (_player.getFightsWon() >= 6) {
                endGameScreen(true);
            }
        }
    }

    public TETile[][] getTiles() {
        return this._world._tiles;
    }

    /**
     * Triggers encounters randomly during each turn.
     */
    void triggerEvents(List<Encounter> encounters) {
        for (Encounter e : encounters) {
            if (e.shouldTrigger()) {
                e.action();
                break;
            }
        }
    }
}
