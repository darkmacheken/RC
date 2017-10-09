/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralserver.processing;

/**
 *
 * @author Asus
 */
public class ClientRequestProcessor implements RequestProcessor {

    @Override
    public Report process(Request request) {
        switch (request.getCommand()) {
            case "LST":
                return new Report();
            case "REQ":
                return new Report();
            case "ERR":
                return new Report();
            default:
                ; // Should never happen
        }
    }
    
}
