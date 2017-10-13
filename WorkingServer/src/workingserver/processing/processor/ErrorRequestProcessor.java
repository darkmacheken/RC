package workingserver.processing.processor;

import workingserver.exceptions.ConnectionException;
import workingserver.processing.report.Report;
import workingserver.processing.report.ReportError;
import workingserver.processing.request.Request;
import workingserver.processing.request.RequestError;
import workingserver.tasks.Task;

/**
 * Processor that process wrong requests (that dont follow the protocol)
 */
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
