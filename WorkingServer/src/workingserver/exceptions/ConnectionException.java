package workingserver.exceptions;

/**
 * Error Exception
 */
public class ConnectionException extends Exception {

    private static final long serialVersionUID = 270920172254L;

    private final String _errorDescription;

    /**
     *
     * @param errorDescription
     */
    public ConnectionException(String errorDescription) {
        _errorDescription=errorDescription;
    }

    /**
     *
     * @return
     */
    public String getErrorDescription() {
        return _errorDescription;
    }

}
