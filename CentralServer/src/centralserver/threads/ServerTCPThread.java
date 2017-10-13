package centralserver.threads;

import centralserver.WSList;
import centralserver.connection.ConnectionTCP;
import centralserver.connection.ServerTCP;
import centralserver.exceptions.ConnectionException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Thread exclusive for Server TCP (just accepts connections and lauch
 * ServerTCPConnectionThread)
 */
public class ServerTCPThread extends Thread {
    private WSList _list;
    private ServerTCP _server;
    
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
                ServerTCPConnectionThread connectionThread = new ServerTCPConnectionThread(connection, _list);
                System.out.println("Connected to client: " + connection.getName() + ":" + connection.getPort());
                connectionThread.start();
            }
            catch (ConnectionException e) {
                System.err.println(e.getErrorDescription());
            }
            catch (IOException ex) {
                Logger.getLogger(ServerTCPThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
