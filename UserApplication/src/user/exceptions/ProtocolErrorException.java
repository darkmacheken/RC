package user.exceptions;

/**
 * Protocol Unexpected Errors 
 */
public class ProtocolErrorException extends ConnectionException {

    private static final long serialVersionUID = 280920170031L;

    private String _message;

    /**
     *
     * @param errorDescription
     * @param message
     */
    public ProtocolErrorException(String errorDescription, String message) {
        super(errorDescription);
        _message=message;
    }

    public String getMessage(){
        return _message;
    }

}
