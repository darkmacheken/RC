/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralserver.processing.processor;

import centralserver.processing.request.Request;
import centralserver.processing.report.Report;
import centralserver.WSList;
import centralserver.connection.ConnectionTCP;
import centralserver.exceptions.ConnectionException;
import centralserver.protocols.ProtocolCSWS;


public class WorkingServerRequestProcessor implements RequestProcessor {
    private RequestToWS _request;
    private ConnectionTCP _connection;
    
    @Override
    public Report process(Request request, WSList list) throws ConnectionException {
        try {
            _request = (RequestToWS) request;
            _connection = new ConnectionTCP(request.getIP(), request.getPort());
            process();
            return null;
        }
        catch (ClassCastException e) {
            // should never reach here
            e.printStackTrace();
            return null;
        }
    }
    
    private void process() throws ConnectionException {
        ProtocolCSWS protocol = new ProtocolCSWS();
        String toSend = protocol.send(_request);
        _connection.send(toSend);
        String received = _connection.receive();
        
    }
    
}
