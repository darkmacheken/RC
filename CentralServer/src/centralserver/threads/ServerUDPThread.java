/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralserver.threads;

import centralserver.WSList;
import centralserver.connection.ServerUDP;
import centralserver.exceptions.ConnectionException;
import centralserver.protocols.ProtocolWSCS;

/**
 *
 *  
 */
public class ServerUDPThread extends Thread {
    WSList _list;
    ServerUDP _server;
    ProtocolWSCS _protocol;
    
    /**
     *
     * @param list
     * @param server
     */
    public ServerUDPThread(WSList list, ServerUDP server) {
        _list = list;
        _server = server;
        _protocol = new ProtocolWSCS(_list);
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                String received = _server.receive();
                String toSend = _protocol.process(received);
                _server.send(toSend);
            }
            catch (ConnectionException e) {
                System.err.println(e.getErrorDescription());
            }
        }
    }
}
