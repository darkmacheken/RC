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
 * @author leonardinho
 */
public class ServerTCPConnectionThread extends Thread {
    ConnectionTCP _connection;

    /**
     *
     * @param connection
     */
    public ServerTCPConnectionThread(ConnectionTCP connection, Task[] tasks) {
        _connection = connection;
    }

    @Override
    public void run() {
        ProtocolCSWS protocol = new ProtocolCSWS(_connection.getName(),
                                                         _connection.getIp(),
                                                         _connection.getPort());
        try {
            ParseProtocolCSWS parser = new ParseProtocolCSWS(_connection);
            String received = parser.parse();
            Request request = protocol.receive(received);
            Report report = request.process();
            String toSend = protocol.send(report);
            _connection.send(toSend);
            _connection.close();
        } catch (ConnectionException e) {
            System.err.println(e.getErrorDescription());
        }
    }
}
