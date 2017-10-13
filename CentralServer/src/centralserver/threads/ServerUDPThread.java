package centralserver.threads;

import centralserver.WSList;
import centralserver.connection.ServerUDP;
import centralserver.exceptions.ConnectionException;
import centralserver.protocols.ProtocolWSCS;

/**
 * Thread exclusive for Server TCP. The regists and unregists from WS occurs 
 * here
 */
public class ServerUDPThread extends Thread {
    private WSList _list;
    private ServerUDP _server;
    private ProtocolWSCS _protocol;
    
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
