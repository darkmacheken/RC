package centralserver.processing;

import centralserver.WSList;

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
     */
    Report process(Request request, WSList list);
}
