/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralserver.processing.processor;

import centralserver.WSList;
import centralserver.exceptions.ConnectionException;
import centralserver.processing.report.Report;
import centralserver.processing.request.Request;
import centralserver.processing.request.RequestToWS;

/**
 *
 * @author Asus
 */
public class WorkingServerRequestProcessor implements RequestProcessor {
    private RequestToWS _request;

    @Override
    public Report process(Request request, WSList list) throws ConnectionException {
        return null;
    }
    
    /**
     * Process and send only
     */
    public void processSend(){
        
    }
    
    /**
     *  Process received. BLOCKING FUNCTION
     * @return
     */
    public Report processReceive(){
        return null;
    }
    
}
