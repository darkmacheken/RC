package centralserver.processing.processor;

import centralserver.WSList;
import centralserver.exceptions.ConnectionException;
import centralserver.processing.report.Report;
import centralserver.processing.report.ReportError;
import centralserver.processing.request.Request;
import centralserver.processing.request.RequestError;

/**
 * Processor that processes the Error Request
 */
public class ClientRequestErrorProcessor implements RequestProcessor {

    /**
     *
     * @param request
     * @param list
     * @return
     */
    @Override
    public Report process(Request request, WSList list) throws ConnectionException {
        try{
            RequestError requestError = (RequestError) request;
            System.out.println("Error request: " + requestError.getError() + " client: " + requestError.getNameAdress() + " " + requestError.getPort());
            return new ReportError(requestError.getNameAdress(),
                                   requestError.getIP(),
                                   requestError.getPort(),
                                   requestError.getError());
        }
        catch(ClassCastException e){
            //should never happen
            e.printStackTrace();
            return null;
        }
    }
    
}
