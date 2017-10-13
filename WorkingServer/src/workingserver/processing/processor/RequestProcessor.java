package workingserver.processing.processor;

import workingserver.exceptions.ConnectionException;
import workingserver.processing.report.Report;
import workingserver.processing.request.Request;
import workingserver.tasks.Task;

/**
 * Interface for the Processors
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
