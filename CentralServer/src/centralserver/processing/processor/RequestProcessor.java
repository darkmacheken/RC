package centralserver.processing.processor;

import centralserver.processing.request.Request;
import centralserver.processing.report.Report;
import centralserver.WSList;
import centralserver.exceptions.ConnectionException;

/**
 *
 * @author Asus
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
