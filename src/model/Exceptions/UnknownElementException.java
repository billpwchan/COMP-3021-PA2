package model.Exceptions;

/**
 * Thrown when the map file has unknown characters
 */
public class UnknownElementException extends InvalidMapException {
    /**
     * @param s The exception message
     */
    public UnknownElementException(String s) {
        super(s);
    }
}
