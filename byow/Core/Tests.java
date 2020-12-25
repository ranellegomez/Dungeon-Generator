package byow.Core;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Tests {
    private static final char[] CHARACTERS =
            "abcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
    private static String _randomString;

    public static void main(String[] args) {
        testNPCRetrieval();
    }

    public static void testNPCRetrieval() {
        NPCSet n = new NPCSet();
        int initSize = n.getSize();
        for (int i = 0; i < initSize; i++) {
            System.out.println(n.getRandomNPC()._name);
        }
    }

    public static void testSimpleFileSaveAndLoad() {
        testSimpleFileSave();
        testSimpleFileLoad();
    }

    public static void testSimpleFileSave() {
        Engine engine = new Engine();
        _randomString = generateRandomString(100);

        engine.setKeyPressesForDebugging(_randomString);
        Engine.saveMovesAndPlayerStats();
    }

    @Test
    public static void testSimpleFileLoad() {
        Engine engine = new Engine();
        String result = Engine.getSavedKeyPresses();
        String expected = _randomString;
        assertEquals(expected, result);
    }

    /**
     * @source https://www.geeksforgeeks.org/generate-random-string-of-given
     * -size-in-java/
     * Given an int N, return a random length-N string consisting of
     * CHARACTERS.
     */
    public static String generateRandomString(int n) {
        StringBuilder randomSB = new StringBuilder(n);
        for (int i = 0; i < n; i += 1) {
            int randomindex = (int) (CHARACTERS.length * Math.random());
            randomSB.append(CHARACTERS[randomindex]);
        }
        return randomSB.toString();
    }

    public static boolean testCovidPositive() {
        return false;
    }
}

