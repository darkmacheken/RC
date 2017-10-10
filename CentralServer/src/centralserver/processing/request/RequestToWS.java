/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralserver.processing.request;

import centralserver.processing.processor.WorkingServerRequestProcessor;


public class RequestToWS extends Request {
    private final String _fileName;
    private final String _pTC;
    
    public RequestToWS(String nameAdress, String iP, int port, String fileName, String pTC, WorkingServerRequestProcessor processor) {
        super(nameAdress, iP, port, processor);
        _fileName = fileName;
        _pTC = pTC;
    }
    
    public String getFileName() {
        return _fileName;
    }

    public String getpTC() {
        return _pTC;
    }
    
}
