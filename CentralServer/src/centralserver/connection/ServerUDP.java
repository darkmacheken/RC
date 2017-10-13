package centralserver.connection;

import centralserver.Constants;
import centralserver.exceptions.ConnectionException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 *
 *  
 */
public class ServerUDP {
    private InetAddress _ipToSend;
    private Integer _portToSend;
    private final Integer _portLocal;
    private DatagramSocket _serverSocket;

    /**
     *
     * @param portLocal
     * @throws ConnectionException
     */
    public ServerUDP(Integer portLocal) throws ConnectionException {
        if (portLocal == null) {
            _portLocal = Constants.DEFAULT_PORT;
        }
        else {
            _portLocal = portLocal;
        }
        createServerSocket();
    }

    private void createServerSocket() throws ConnectionException {
        try {
            _serverSocket = new DatagramSocket(_portLocal);
        }
        catch (SocketException ex) {
            throw new ConnectionException("[UDP] " + Constants.SERVER_IOERR + _portLocal + "\n");
        }
    }

    /**
     *
     * @return
     * @throws ConnectionException
     */
    public String receive() throws ConnectionException {
        byte[] buf = new byte[1024];
        DatagramPacket packet;
        packet = new DatagramPacket(buf, buf.length);
        try {
            _serverSocket.receive(packet);
        }
        catch (IOException ex) {
            throw new ConnectionException("[UDP] " + Constants.SOCK_READERR);
        }
        _ipToSend = packet.getAddress();
        _portToSend = packet.getPort();
        String received = new String(packet.getData(), 0, packet.getLength());
        return received;
    }

    /**
     *
     * @param message
     * @throws ConnectionException
     */
    public void send(String message) throws ConnectionException {
        byte[] buf = new byte[20];
        buf = message.getBytes();
        DatagramPacket packet;
        packet = new DatagramPacket(buf, buf.length, _ipToSend, _portToSend);
        try {
            _serverSocket.send(packet);
        }
        catch (IOException ex) {
            throw new ConnectionException("[UDP] " + Constants.SOCKET_SENDERR);
        }
    }

    /**
     *
     */
    public void close() {
        _serverSocket.close();
    }
}
