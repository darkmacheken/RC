package workingserver.processing.request;

import workingserver.processing.processor.ErrorRequestProcessor;

/**
 * Requests that dont follow the protocol
 */
public class RequestError extends Request {
    private final String _error;

    /**
     *
     * @param error
     */
    public RequestError(String error) {
        super(new ErrorRequestProcessor());
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
