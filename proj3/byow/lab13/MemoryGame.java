package byow.lab13;

import byow.Core.InputSource;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.util.Random;

public class MemoryGame {
    private static final char[] CHARACTERS =
            "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT =
            {"You can do this!", "I believe in you!",
                    "You got this!", "You're a star!", "Go Bears!",
                    "Too easy for you!", "Wow, so impressive!"};
    private final int width;
    private final int height;
    private final Random rand;
    private int round;
    private boolean gameOver;
    private boolean playerTurn;

    public MemoryGame(int width, int height, long seed) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16
        squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom
         right is (width, height)
         */
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();
        rand = new Random(seed);
    }

    public static void main(String[] args) {
        /**
         if (args.length < 1) {
         System.out.println("Please enter a seed");
         return;
         }
         */
        long seed = Long.parseLong(args[0]);
        MemoryGame game = new MemoryGame(40, 40, seed);
        game.startGame();
    }

    public static long parseSeedString(InputSource input, boolean render) {
        char next = input.getNextKey();
        StringBuilder seed = new StringBuilder();
        seed.append(next);
        if (render) {
            displaySeedScreen(seed.toString());
        }
        while (next != 's') {
            if (render) {
                displaySeedScreen(seed.toString());
            }
            next = input.getNextKey();
            seed.append(next);
        }
        seed.deleteCharAt(seed.length() - 1);
        return Long.parseLong(seed.toString());
    }

    private static void displaySeedScreen(String seed) {
        StdDraw.clear();
        StdDraw.clear(Color.BLACK);
        System.out.println(seed);
        StdDraw.setPenColor(Color.RED);
        StdDraw.text(5 / 2, (float) 2 * 6 / 3,
                     "Please input a seed.");
        StdDraw.text(5 / 2, 6 / 2, seed);
        StdDraw.show();
    }

    /**
     * @source https://www.geeksforgeeks.org/generate-random-string-of-given
     * -size-in-java/
     */
    public String generateRandomString(int n) {
        StringBuilder randomSB = new StringBuilder(n);
        for (int i = 0; i < n; i += 1) {
            int randomIndex = rand.nextInt(CHARACTERS.length);
            randomSB.append(CHARACTERS[randomIndex]);
        }
        return randomSB.toString();
    }

    public void drawFrame(String s) {
        StdDraw.clear();
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.text(1.0 * width / 2, 1.0 * height / 2, s);
        StdDraw.show();
    }

    public void flashSequence(String letters) {
        for (char c : letters.toCharArray()) {
            drawFrame(Character.toString(c));
            StdDraw.pause(1000);
            drawFrame("");
            StdDraw.pause(500);
        }

    }

    public String solicitNCharsInput(int n) {
        StringBuilder sb = new StringBuilder();
        drawFrame(sb.toString());
        for (int i = sb.length(); i < n; i = sb.length()) {
            if (!StdDraw.hasNextKeyTyped()) {
                continue;
            }
            sb.append(StdDraw.nextKeyTyped());
            drawFrame(sb.toString());
        }
        return sb.toString();
    }

    public void startGame() {
        round = 1;
        while (true) {
            drawFrame("Round " + round);
            String randomString = generateRandomString(round);
            flashSequence(randomString);
            if (solicitNCharsInput(round).equals(randomString)) {
                round++;
            } else {
                break;
            }
        }
        drawFrame("Game Over! You made it to round: " + round);
        return;
    }
}
