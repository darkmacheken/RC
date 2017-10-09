/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralserver.processing;

import centralserver.WSList;

/**
 *
 * @author Asus
 */
public class ClientRequestProcessor implements RequestProcessor {
    private WSList _list;

    @Override
    public Report process(Request request, WSList list) {
        _list = list;
        
        switch (request.getCommand()) {
            case "LST":
                String[] ptc = _list.getPTC();
                return new Report(request.getCommand(), ptc);
            case "REQ":
                
                return new Report(request.getCommand(), null, );
            case "ERR":
                return new Report(request.getCommand(), null, null, 0, null);
            default:
                ; // Should never happen
        }
    }
    
    private Report
    
}
