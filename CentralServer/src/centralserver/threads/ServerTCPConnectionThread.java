/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralserver.threads;

import centralserver.WSList;
import centralserver.connection.ConnectionTCP;
import centralserver.exceptions.ConnectionException;
import centralserver.processing.Report;
import centralserver.processing.RequestOk;
import centralserver.protocols.ProtocolCSClient;

/**
 *
 * @author duartegalvao
 */
public class ServerTCPConnectionThread extends Thread {
    ConnectionTCP _connection;
    WSList _list;
    
    /**
     *
     * @param connection
     * @param list
     */
    public ServerTCPConnectionThread(ConnectionTCP connection, WSList list) {
        _connection = connection;
        _list = list;
    }
    
    @Override
    public void run() {
        ProtocolCSClient protocol = new ProtocolCSClient(_connection.getName(),
                                                         _connection.getIp(),
                                                         _connection.getPort());
        try {
            String received = _connection.receive();
            RequestOk request = protocol.receive(received);
            Report report = request.process(_list);
            String toSend = protocol.send(report);
            _connection.send(toSend);
            _connection.close();
        } catch (ConnectionException e) {
            System.err.println(e.getErrorDescription());
        }
    }
}
