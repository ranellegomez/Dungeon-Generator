package byow.Core;


public class StringInputSource implements InputSource {

    private final String _input;
    private int _index;

    /**
     * Given a String S, construct a StringInputSource object with _index =
     * 0 and _input = S.
     */
    public StringInputSource(String s) {
        _index = 0;
        _input = s;
    }

    /**
     * Retrieve the Character in this StringInputSource at _index. Increment
     * _index by 1 and return the retrieved Character.
     */
    public char getNextKey() {
        char nextChar = _input.charAt(_index++);
        return nextChar;
    }

    /**
     * Return true if there are any characters left to be returned; return
     * false otherwise.
     */
    public boolean possibleNextInput() {
        return _index < _input.length();
    }

}
