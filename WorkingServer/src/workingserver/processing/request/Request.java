/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workingserver.processing.request;

import workingserver.processing.report.Report;
import workingserver.exceptions.ConnectionException;
import workingserver.processing.processor.RequestProcessor;

/**
 *
 * @author Pedro Daniel
 */
public abstract class Request {
    
     //Processor class of the request
    private final RequestProcessor _processor;

    /**
     *
     * @param _processor
     */
    public Request(RequestProcessor _processor) {
        this._processor = _processor;
    }

    /**
     *
     * @return
     */
    public RequestProcessor getProcessor() {
        return _processor;
    }
    
    /**
     *
     * @param list
     * @return
     * @throws centralserver.exceptions.ConnectionException
     */
    public Report process() throws ConnectionException {
        return _processor.process(this);
    }
     
}
