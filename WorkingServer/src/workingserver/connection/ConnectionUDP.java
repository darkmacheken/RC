package workingserver.connection;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ConnectionUDP {
    private String _nameToSend;
    private InetAddress _ipToSend;
    private Integer _portToSend;
    private DatagramSocket _clientSocket;

    public ConnectionUDP(String nameToSend, Integer portToSend) throws ConnectionException {
        if (port == null) {
            _portToSend = Constants.DEFAULT_PORT;
        }
        else {
            _portToSend = port;
        }
        if (nameToSend == null) {
            _nameToSend = Constants.DEFAULT_HOST;
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

    public void close() {
        _serverSocket.close();
    }
}
