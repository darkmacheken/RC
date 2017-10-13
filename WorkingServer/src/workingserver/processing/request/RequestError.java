/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workingserver.processing.request;

import workingserver.processing.processor.ErrorRequestProcessor;

/**
 *
 *  
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
