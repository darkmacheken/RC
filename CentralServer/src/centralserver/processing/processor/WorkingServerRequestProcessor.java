/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralserver.processing.processor;

import centralserver.WSList;
import centralserver.connection.ConnectionTCP;
import centralserver.exceptions.ConnectionException;
import centralserver.processing.report.Report;
import centralserver.processing.request.Request;
import centralserver.processing.request.RequestToWS;
import centralserver.protocols.ParseProtocolCSWS;
import centralserver.protocols.ProtocolCSWS;

/**
 *
 *  
 */
public class WorkingServerRequestProcessor implements RequestProcessor {
   private ConnectionTCP _connection;
   private ProtocolCSWS _protocol;
   private RequestToWS _request;

    @Override
    public Report process(Request request, WSList list) throws ConnectionException {
        return null;
    }

    /**
     * Process and send only
     * @param request
     * @throws centralserver.exceptions.ConnectionException
     */
    public void processSend(RequestToWS request) throws ConnectionException {
        _request = request;
        _protocol = new ProtocolCSWS(_request.getNameAdress(), _request.getIP(), _request.getPort());
        _connection = new ConnectionTCP(_request.getIP(), _request.getPort());
        String toSend = _protocol.send(_request);
        System.out.println("Making " + _request.getpTC() + " request to WS " + _request.getNameAdress() + " " + _request.getPort() + ": " +
                           "\n\tFile Name: " + _request.getFileName() + "\n\t" + _request.getFile().length() + " bytes to transmit.");
        _connection.send(toSend);
    }

    /**
     *  Process received. BLOCKING FUNCTION
     * @return
     * @throws centralserver.exceptions.ConnectionException
     */
    public Report processReceive() throws ConnectionException {
      ParseProtocolCSWS parser= new ParseProtocolCSWS(_connection);
       String received = parser.parse();
       Report report = _protocol.receive(received);

       _connection.close();

       return report;
    }

}
