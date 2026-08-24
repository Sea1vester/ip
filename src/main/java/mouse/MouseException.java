package mouse;

/**
 * A user-facing error from an invalid Mouse command.
 */
public class MouseException extends Exception {
    
    public MouseException(String message) {
        super(message);
    }
}
