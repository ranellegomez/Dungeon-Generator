package byow.Core;

/**
 * An interface for user-inputted commands.
 */
public interface InputSource {
    char getNextKey();

    boolean possibleNextInput();
}
