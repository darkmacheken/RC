/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workingserver.threads;

import workingserver.connection.ConnectionTCP;
import workingserver.exceptions.ConnectionException;
import workingserver.processing.report.Report;
import workingserver.processing.request.Request;
import workingserver.protocols.ProtocolWSCS;

/**
 *
 * @author duartegalvao
 */
public class ServerTCPConnectionThread extends Thread {
    ConnectionTCP _connection;
    
    /**
     *
     * @param connection
     */
    public ServerTCPConnectionThread(ConnectionTCP connection) {
        _connection = connection;
    }
    
    @Override
    public void run() {
        ProtocolCSClient protocol = new ProtocolCSClient(_connection.getName(),
                                                         _connection.getIp(),
                                                         _connection.getPort());
        try {
            ParseProtocolClientCS parser = new ParseProtocolClientCS(_connection);
            String received = parser.parse();
            Request request = protocol.receive(received);
            Report report = request.process(_list);
            String toSend = protocol.send(report);
            _connection.send(toSend);
            _connection.close();
        } catch (ConnectionException e) {
            System.err.println(e.getErrorDescription());
        }
    }
}
