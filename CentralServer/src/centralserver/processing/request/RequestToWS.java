/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralserver.processing.request;

import centralserver.exceptions.ConnectionException;
import centralserver.processing.processor.WorkingServerRequestProcessor;
import centralserver.processing.report.Report;

/**
 *
 *  
 */
public class RequestToWS extends Request {
    private final String _fileName;
    private final String _pTC;
    private final String _file;
    private final WorkingServerRequestProcessor _workingProcessor;
      
    /**
     *
     * @param iP
     * @param port
     * @param fileName
     * @param pTC
     * @param file
     * @param processor
     */
    public RequestToWS(String iP, int port,
                       String fileName, String pTC, String file, WorkingServerRequestProcessor processor) {
        super(iP, iP, port, null);
        _fileName = fileName;
        _pTC = pTC;
        _file = file;
        _workingProcessor = processor;
    }
    
    /**
     * Process request and send only the message
     * @throws centralserver.exceptions.ConnectionException
     */
    public void processSend() throws ConnectionException{
        _workingProcessor.processSend(this);
    }
    
    /**
     * Receive from WS and process
     * @return 
     * @throws centralserver.exceptions.ConnectionException 
     */
    public Report processReceive() throws ConnectionException{
        return _workingProcessor.processReceive();
    }
    
    /**
     *
     * @return
     */
    public String getFileName() {
        return _fileName;
    }

    /**
     *
     * @return
     */
    public String getpTC() {
        return _pTC;
    }

    /**
     *
     * @return
     */
    public String getFile() {
        return _file;
    }
    
}
