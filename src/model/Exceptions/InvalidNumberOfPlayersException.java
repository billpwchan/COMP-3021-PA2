package model.Exceptions;

/**
 * There should only ever be one player. Otherwise, throw this exception.
 */
public class InvalidNumberOfPlayersException extends InvalidMapException {
    /**
     * @param s The exception message
     */
    public InvalidNumberOfPlayersException(String s) {
        super(s);
    }
}
