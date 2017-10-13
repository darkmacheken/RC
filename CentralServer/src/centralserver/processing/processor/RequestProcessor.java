package centralserver.processing.processor;

import centralserver.WSList;
import centralserver.exceptions.ConnectionException;
import centralserver.processing.report.Report;
import centralserver.processing.request.Request;

/**
 * Interface for the Processors
 */
public interface RequestProcessor {

    /**
     *
     * @param request
     * @param list
     * @return
     * @throws centralserver.exceptions.ConnectionException
     */
    Report process(Request request, WSList list) throws ConnectionException;
}
