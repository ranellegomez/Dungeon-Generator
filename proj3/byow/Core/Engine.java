package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashSet;

public class Engine {

    public static final int WIDTH = 160;
    public static final int HEIGHT = 90;
    static final File SAVED_DATA = new File("./saved_data.txt");
    private static final StringBuilder MOVESANDPLAYERSTATS =
            new StringBuilder();
    private static final RandomStringFactory _randomStringFactory =
            new RandomStringFactory();
    private static String _characterName;


    private static void setup() {
        StdDraw.setCanvasSize((WIDTH - 1) * 16, (HEIGHT - 1) * 16);
        StdDraw.setXscale(0, WIDTH);
        StdDraw.setYscale(0, HEIGHT);
        StdDraw.enableDoubleBuffering();
        StdDraw.clear(Color.BLACK);
        StdDraw.show();
    }

    /**
     * Open the GUI window with the main menu.
     */
    private static void drawMainMenu() {
        solicitNamePreference(new KeyboardInputSource());
        StdDraw.clear(Color.BLACK);
        Font font = new Font("Monaco", Font.ITALIC, 40);
        StdDraw.setFont(font);
        StdDraw.setPenColor(Color.RED);
        StdDraw.text(1.0 * WIDTH / 2, 1.0 * HEIGHT / 1.5,
                     getCharacterName() + ": " + "The Game "
                             + getRandomUnicodeFace());
        Font tinyFont = new Font("Monaco", Font.PLAIN, 10);
        StdDraw.setFont(tinyFont);
        StdDraw.textLeft(1, HEIGHT - 1, "Made with love"
                + " in Berkeley and Lomita, "
                + "California"
                + " by Sherry Fan and Ranelle Gomez in 2020.");
        Font optionsFont = new Font("Monaco", Font.ITALIC, 30);
        StdDraw.setFont(optionsFont);
        StdDraw.setPenColor(Color.RED);
        StdDraw.text(1.0 * WIDTH / 2, 1.0 * HEIGHT / 2,
                     "New Game (N)" + "\n" + "Load Game (L)" + "\n" + "Quit "
                             + "(Q)");
        StdDraw.show();
    }

    private static void solicitNamePreference(InputSource input) {
        int menuOptionNumber;
        displayNameOptionsScreen();
        while (true) {
            if (input.possibleNextInput()) {
                char nextKey = input.getNextKey();
                if (nextKey == '1' || nextKey == '2') {
                    menuOptionNumber =
                            Integer.parseInt(Character.toString(nextKey));
                    break;
                }
            }
        }
        if (menuOptionNumber == 1) {
            setDefaultCharacterName();
        } else {
            setRandomName();
        }
    }

    private static void displayNameOptionsScreen() {
        StdDraw.clear();
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.RED);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.text(1.0 * WIDTH / 2, (1.0 * HEIGHT / 2) + 3,
                     "How shall our hero be called? ¯\\_(ツ)_/¯");
        StdDraw.text(1.0 * WIDTH / 2 - 1, (1.0 * HEIGHT / 2), "(1) Super "
                + "Mariusz" + "\n" + "(2) Let fate decide!");
        StdDraw.show();
    }

    private static void setRandomName() {
        _characterName = _randomStringFactory.getRandomName();
        Tileset.setAvatarName(_characterName);
    }

    private static String getRandomUnicodeFace() {
        return _randomStringFactory.getRandomUnicodeFace();
    }

    private static void setDefaultCharacterName() {
        _characterName = "Super Mariusz";
        Tileset.setAvatarName(_characterName);
    }

    private static String getCharacterName() {
        return _characterName;
    }

    /**
     * Remove all but the last ":q".
     */
    private static String replaceAllButLastQuit(String s) {
        String removed = s.replaceAll(":q", "");
        return removed + ":q";
    }

    private static void preprocessKeypresses() {
        String quitAtEnd =
                replaceAllButLastQuit(MOVESANDPLAYERSTATS.toString());
        MOVESANDPLAYERSTATS.delete(0, MOVESANDPLAYERSTATS.length());
        MOVESANDPLAYERSTATS.append(quitAtEnd);
    }

    private static TETile[][] startGame(InputSource input, boolean render)
            throws IOException, URISyntaxException {
        if (render) {
            drawMainMenu();
        }
        String prevKeys = getSavedKeyPresses();
        if (render) {
            MOVESANDPLAYERSTATS.append(prevKeys);
        }
        char next;
        while (true) {
            if (input.possibleNextInput()) {
                next = input.getNextKey();
                if (next == 'n' || next == 'l' || next == 'q') {
                    break;
                }
            }
        }
        MOVESANDPLAYERSTATS.append(next);
        long seed;
        Game g = null;
        Player player = new Player(Tileset.AVATAR);
        if (next == 'l') {
            StringInputSource prev =
                    new StringInputSource(getSavedKeyPresses());
            prev.getNextKey();
            seed = parseSeedString(prev, false);
            player = getLastSavedPlayer();
            g = new Game(seed, player);
            g.play(prev, false);
        } else if (next == 'n') {
            MOVESANDPLAYERSTATS.delete(1, MOVESANDPLAYERSTATS.length());
            if (render) {
                displaySeedScreen("");
            }
            seed = parseSeedString(input, render);

            MOVESANDPLAYERSTATS.append(seed);
            MOVESANDPLAYERSTATS.append("s");
            g = new Game(seed, player);
        } else {
            drawQuitMenuScreen();
        }
        String movements = g.play(input, render);
        MOVESANDPLAYERSTATS.append(movements);
        preprocessKeypresses();
        saveMovesAndPlayerStats();
        if (render) {
            drawQuitScreen();
        }
        return g.getTiles();
    }

    private static void drawQuitScreen() {
        StdDraw.clear();
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.CYAN);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.text(WIDTH / 2, HEIGHT / 2, "Thanks for playing!");
        StdDraw.show();
        StdDraw.pause(3000);
        System.exit(0);
    }

    private static void drawQuitMenuScreen() {
        StdDraw.clear();
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.CYAN);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.text(WIDTH / 2, HEIGHT / 2, "See you next time!");
        StdDraw.show();
        StdDraw.pause(3000);
        System.exit(0);
    }

    private static void displaySeedScreen(String seed) {
        StdDraw.clear();
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.RED);
        StdDraw.text(WIDTH / 2, (float) 2 * HEIGHT / 3,
                     "Please input a seed.");
        StdDraw.text(WIDTH / 2, HEIGHT / 2, seed);
        StdDraw.show();
    }

    public static long parseSeedString(InputSource input, boolean render) {
        HashSet validChars = new HashSet(
                Arrays.asList('s', '0', '1', '2', '3', '4', '5', '6', '7', '8'
                        , '9'));
        StringBuilder seed = new StringBuilder();
        while (true) {
            char next = input.getNextKey();
            if (validChars.contains(next)) {
                if (next == 's') {
                    break;
                } else {
                    seed.append(next);
                    if (render) {
                        displaySeedScreen(seed.toString());
                    }

                }
            }

        }
        return Long.parseLong(seed.toString());
    }

    /**
     * @source: https://stackabuse.com/java-save-write-string-into-a-file
     * /#files.writestring
     * Convert _keyPresses to a String and write it to saved_data.txt.
     */
    public static void saveMovesAndPlayerStats() {
        if (MOVESANDPLAYERSTATS.length() == 0) {
            return;
        }
        try {
            if (!SAVED_DATA.exists()) {
                SAVED_DATA.createNewFile();
            }
            Files.writeString(SAVED_DATA.toPath(),
                              MOVESANDPLAYERSTATS.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Return the string in the saved_data.txt file.
     */
    static String getSavedKeyPresses() {
        if (SAVED_DATA.exists()) {
            try {
                FileReader fileReader = new FileReader(SAVED_DATA);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                return bufferedReader.readLine().concat(":q");
            } catch (FileNotFoundException e) {
                System.out.println("file not found");
                System.exit(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static Player getLastSavedPlayer() {
        if (SAVED_DATA.exists()) {
            try {
                FileReader fileReader = new FileReader(SAVED_DATA);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                bufferedReader.readLine();
                String playerHealth = bufferedReader.readLine();
                String playerCharisma = bufferedReader.readLine();
                String playerWinCount =
                        bufferedReader.readLine().replaceFirst(":q", "");
                return new Player(Tileset.AVATAR,
                                  Integer.parseInt(playerHealth),
                                  Integer.parseInt(playerCharisma),
                                  Integer.parseInt(playerWinCount));
            } catch (FileNotFoundException e) {
                System.out.println("file not found");
                System.exit(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Given a String S, append S to _KeyPresses. This method is implemented
     * to facilitate debugging and will not be used in the actual
     * implementation.
     */
    public void setKeyPressesForDebugging(String s) {
        MOVESANDPLAYERSTATS.append(s);
    }

    /**
     * Method used for exploring a fresh world. This method should handle all
     * inputs,
     * including inputs from the main menu.
     */
    public void interactWithKeyboard() throws IOException, URISyntaxException {
        setup();
        startGame(new KeyboardInputSource(), true);
    }

    /**
     * Method used for autograding and testing your code. The input string
     * will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q",
     * "lwww". The engine should
     * behave exactly as if the user typed these characters into the engine
     * using
     * interactWithKeyboard.
     * <p>
     * Recall that strings ending in ":q" should cause the game to quite save
     * . For example,
     * if we do interactWithInputString("n123sss:q"), we expect the game to
     * run the first
     * 7 commands (n123sss) and then quit and save. If we then do
     * interactWithInputString("l"), we should be back in the exact same state.
     * <p>
     * In other words, both of these calls:
     * - interactWithInputString("n123sss:q")
     * - interactWithInputString("lww")
     * <p>
     * should yield the exact same world state as:
     * - interactWithInputString("n123sssww")
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] interactWithInputString(String input)
            throws IOException, URISyntaxException {
        input = replaceAllButLastQuit(input);
        StringInputSource inputSrc = new StringInputSource(input);
        return startGame(inputSrc, false);
    }

}
