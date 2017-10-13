package workingserver.processing.processor;

import workingserver.GlobalFunctions;
import workingserver.exceptions.ConnectionException;
import workingserver.processing.report.Report;
import workingserver.processing.report.ReportError;
import workingserver.processing.request.Request;
import workingserver.processing.request.RequestOk;
import workingserver.tasks.Task;
import workingserver.tasks.TaskRunner;

/**
 * Processor that process requests that follow the protocol
 */
public class OkRequestProcessor implements RequestProcessor {
    private RequestOk _request;
    private Task[] _tasks;

    @Override
    public Report process(Request request, Task[] tasks) throws ConnectionException {
        try {
            _request = (RequestOk) request;
            _tasks = tasks;
            return process();
        }
        catch (ClassCastException e) {
            // should never happen
            e.printStackTrace();
            return null;
        }
    }
    
    private Report process() throws ConnectionException {
        String fileName = _request.getFileName();
        String file = _request.getFile();
        
        GlobalFunctions.writeToFile("input_files/" + fileName, file);
        
        if (_request.getCommand().equals("WRQ")) {
            // Process request from Central Server
            TaskRunner runner = new TaskRunner(_tasks, _request.getPTC(), file);
            return runner.run();
        }
        return new ReportError("ERR");
    }
    
}
