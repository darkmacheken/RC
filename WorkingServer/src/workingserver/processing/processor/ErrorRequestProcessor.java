/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workingserver.processing.processor;

import workingserver.exceptions.ConnectionException;
import workingserver.processing.report.Report;
import workingserver.processing.report.ReportError;
import workingserver.processing.request.Request;
import workingserver.processing.request.RequestError;
import workingserver.tasks.Task;


public class ErrorRequestProcessor implements RequestProcessor {

    @Override
    public Report process(Request request, Task[] tasks) throws ConnectionException {
        try{
            RequestError requestError = (RequestError) request;
            System.out.println("Error request: " + requestError.getError());
            return new ReportError(requestError.getError());
        }
        catch(ClassCastException e){
            //should never happen
            e.printStackTrace();
            return null;
        }
    }
    
}
