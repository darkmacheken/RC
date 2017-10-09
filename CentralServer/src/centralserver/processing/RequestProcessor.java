package centralserver.processing;

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
