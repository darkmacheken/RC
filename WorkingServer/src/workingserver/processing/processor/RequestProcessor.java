package workingserver.processing.processor;

import workingserver.processing.request.Request;
import workingserver.processing.report.Report;
import workingserver.exceptions.ConnectionException;

/**
 *
 * @author Asus
 */
public interface RequestProcessor {

    /**
     *
     * @param request
     * @return
     * @throws workingserver.exceptions.ConnectionException
     */
    Report process(Request request) throws ConnectionException;
}
