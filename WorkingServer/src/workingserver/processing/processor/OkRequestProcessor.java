/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workingserver.processing.processor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import workingserver.Constants;
import workingserver.exceptions.ConnectionException;
import workingserver.processing.report.Report;
import workingserver.processing.report.ReportError;
import workingserver.processing.request.Request;
import workingserver.processing.request.RequestOk;
import workingserver.tasks.Task;
import workingserver.tasks.TaskRunner;


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
        
        // Save file
        try {
            PrintWriter out = new PrintWriter(
                    new BufferedWriter(
                            new FileWriter("input_files/" + fileName)));
            out.print(file);
            out.close();
        }
        catch (IOException e) {
            throw new ConnectionException(Constants.FILE_CNTWRT);
        }
        
        if (_request.getCommand().equals("WRQ")) {
            // Process request from Central Server
            TaskRunner runner = new TaskRunner(_tasks, _request.getPTC(), file);
            return runner.run();
        }
        return new ReportError("ERR");
    }
    
}
