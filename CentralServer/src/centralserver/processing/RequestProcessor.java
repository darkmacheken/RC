package centralserver.processing;

/**
 *
 * @author Asus
 */
public interface RequestProcessor {

    /**
     *
     * @param request
     * @return
     */
    Report process(Request request);
}
