/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workingserver.processing.processor;

import workingserver.exceptions.ConnectionException;
import workingserver.processing.report.Report;
import workingserver.processing.request.Request;
import workingserver.tasks.Task;


public class ErrorRequestProcessor implements RequestProcessor {

    @Override
    public Report process(Request request, Task[] tasks) throws ConnectionException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
