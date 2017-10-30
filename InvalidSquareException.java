/**
 * Represents an exception made when an invalid Square of invalid rank, invalid
 * file, or invalid name is created. I chose to make my exception a
 * Represents an exception made when an invalid Square of invalid rank or
 * file is created. I chose to make my exception a
 * checked exception since a square can't be
 * changed to be made a valid square during runtime.
 * Before any methods can be called,
 * a square must be valid, and a checked exception makes sure
 * it's handled before the square can be used for movement
 * with a chess Piece. An invalid chess piece can't be avoided
 * beforehand, and so an invalid square is an expected condition
 * and should be handled with a checked exception.
 *
 *
 * @see Square, Piece
 * @version 1.0
 * @author mabdi3
 */
public class InvalidSquareException extends RuntimeException {
    private String message;

    /**
     * Creates InvalidSquareException with all the required paramaters
     * @param message the invalid square as a String
     */
    public InvalidSquareException(String message) {
        super(message);
    }


}