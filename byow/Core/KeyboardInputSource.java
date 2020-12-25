package byow.Core;

/**
 * Created by hug.
 */

import edu.princeton.cs.introcs.StdDraw;

public class KeyboardInputSource implements InputSource {
    private static final boolean PRINT_TYPED_KEYS = false;

    public KeyboardInputSource() {
    }

    public char getNextKey() {
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char c = StdDraw.nextKeyTyped();
                if (PRINT_TYPED_KEYS) {
                    System.out.print(c);
                }
                return Character.toLowerCase(c);
            }
        }
    }

    public boolean possibleNextInput() {
        return StdDraw.hasNextKeyTyped();
    }

}
