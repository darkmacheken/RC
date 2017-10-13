package centralserver.processing.request;

import centralserver.processing.processor.RequestProcessor;

/**
 * Requests that doesnt follow the protocol
 */
public class RequestError extends Request {
    private final String _error;

    /**
     *
     * @param nameAdress
     * @param iP
     * @param port
     * @param error
     * @param processor
     */
    public RequestError(String nameAdress, String iP, int port, String error, RequestProcessor processor) {
        super(nameAdress, iP, port, processor);
        _error = error;
    }
    
    /**
     *
     * @return
     */
    public String getError() {
        return _error;
    }

    
}
