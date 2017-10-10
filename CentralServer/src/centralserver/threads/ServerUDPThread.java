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

/**
 *
 * @author duartegalvao
 */
public class ServerUDPThread extends Thread {
    WSList _list;
    ServerUDP _server;
    
    /**
     *
     * @param list
     * @param server
     */
    public ServerUDPThread(WSList list, ServerUDP server) {
        _list = list;
        _server = server;
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                ConnectionUDP connection = _server.acceptSocket();
                ServerUDPConnectionThread connectionThread = new ServerUDPConnectionThread(connection, _list);
                System.out.println("Connected to client: " + connection.getName() + ":" + connection.getPort());
                connectionThread.start();
            }
            catch (ConnectionException e) {
                System.err.println(e.getErrorDescription());
            }
        }
    }
}
