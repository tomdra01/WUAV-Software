package dk.easv.bll.exception;

/**
 *
 * @author tomdra01, mrtng1
 */
public class GUIException extends RuntimeException{
    public GUIException(String message, Exception e) {
        super(message, e);
    }
    public GUIException(String message) {
        super(message);
    }
}
