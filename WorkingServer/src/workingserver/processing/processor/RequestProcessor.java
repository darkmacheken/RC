package workingserver.processing.processor;

import workingserver.processing.request.Request;
import workingserver.processing.report.Report;
import workingserver.exceptions.ConnectionException;
import workingserver.tasks.Task;

/**
 *
 *  
 */
public interface RequestProcessor {

    /**
     *
     * @param request
     * @param tasks
     * @return
     * @throws workingserver.exceptions.ConnectionException
     */
    Report process(Request request, Task[] tasks) throws ConnectionException;
}
