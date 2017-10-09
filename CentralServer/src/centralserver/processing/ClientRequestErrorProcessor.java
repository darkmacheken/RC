/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralserver.processing;

import centralserver.WSList;
import centralserver.exceptions.ConnectionException;

/**
 *
 * @author Asus
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
            return new ReportError(requestError.getNameAdress(),
                                   requestError.getiP(),
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
