/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralserver.processing.processor;

import centralserver.processing.request.Request;
import centralserver.processing.report.Report;
import centralserver.WSList;
import centralserver.exceptions.ConnectionException;


public class WorkingServerRequestProcessor implements RequestProcessor {

    @Override
    public Report process(Request request, WSList list) throws ConnectionException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
