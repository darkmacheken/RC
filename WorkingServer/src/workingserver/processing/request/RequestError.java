/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workingserver.processing.request;

import workingserver.processing.processor.RequestProcessor;

/**
 *
 * @author Asus
 */
public class RequestError extends Request {
    private final String _error;

    /**
     *
     * @param error
     * @param processor
     */
    public RequestError(String error, RequestProcessor processor) {
        super(processor);
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
