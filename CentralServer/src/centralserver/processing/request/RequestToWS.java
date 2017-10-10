/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralserver.processing.request;

import centralserver.processing.processor.WorkingServerRequestProcessor;
import centralserver.processing.report.Report;

/**
 *
 * @author Asus
 */
public class RequestToWS extends Request {
    private final String _fileName;
    private final String _pTC;
    private final String _file;
    private final WorkingServerRequestProcessor _workingProcessor;
      
    /**
     *
     * @param nameAdress
     * @param iP
     * @param port
     * @param fileName
     * @param pTC
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
     */
    public void processSend(){
        _workingProcessor.processSend();
    }
    
    /**
     * Receive from WS and process
     */
    public Report processReceive(){
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
