/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralserver.threads;

import centralserver.WSList;
import centralserver.connection.ConnectionUDP;
import centralserver.connection.ServerUDP;
import centralserver.exceptions.ConnectionException;
import centralserver.protocols.ProtocolWSCS;

/**
 *
 * @author duartegalvao
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
                String recv = _server.receive();
                String rproc = _protocol.process(s);
                
                ConnectionUDP connection = _server.acceptSocket();
                System.out.println("Connected to client: " + connection.getName() + ":" + connection.getPort());
                connectionThread.start();
            }
            catch (ConnectionException e) {
                System.err.println(e.getErrorDescription());
            }
        }
    }
}
