/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralserver.threads;

import centralserver.WSList;
import centralserver.connection.ConnectionTCP;
import centralserver.connection.ServerTCP;
import centralserver.exceptions.ConnectionException;

/**
 *
 * @author Pedro Daniel
 */
public class ServerTCPThread extends Thread {
    WSList _list;
    ServerTCP _server;
    
    /**
     *
     * @param list
     * @param server
     */
    public ServerTCPThread(WSList list, ServerTCP server) {
        _list = list;
        _server = server;
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                ConnectionTCP connection = _server.acceptSocket();
                new ServerTCPConnectionThread(connection, _list).start();
            }
            catch (ConnectionException e) {
                System.err.println(e.getErrorDescription());
            }
        }
    }
}
