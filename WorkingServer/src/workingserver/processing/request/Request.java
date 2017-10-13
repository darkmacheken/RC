/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workingserver.processing.request;

import workingserver.exceptions.ConnectionException;
import workingserver.processing.processor.RequestProcessor;
import workingserver.processing.report.Report;
import workingserver.tasks.Task;

/**
 *
 *  
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
     * @param tasks
     * @return
     * @throws ConnectionException
     */
    public Report process(Task[] tasks) throws ConnectionException {
        return _processor.process(this, tasks);
    }
     
}
