package workingserver.connection;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import workingserver.Constants;
import workingserver.exceptions.ConnectionException;

public class ConnectionUDP {
    private String _nameToSend;
    private InetAddress _ipToSend;
    private Integer _portToSend;
    private DatagramSocket _clientSocket;

    public ConnectionUDP(String nameToSend, Integer portToSend) throws ConnectionException {
        if (portToSend == null) {
            _portToSend = Constants.DEFAULT_PORT;
        }
        else {
            _portToSend = portToSend;
        }
        if (nameToSend == null) {
            _nameToSend = Constants.DEFAULT_HOST;
        }
        try {
            _ipToSend = InetAddress.getByName(_nameToSend);
        }
        catch (UnknownHostException e) {
            throw new ConnectionException(Constants.SOCK_UHOST + _nameToSend + "\n");
        }
        createSocket();
        try {
            _clientSocket.setSoTimeout(Constants.TIMEOUT);
        }
        catch (SocketException e) {
            throw new ConnectionException(Constants.SOCKET_TIMEOUTERR);
        }
    }

    private void createSocket() throws ConnectionException {
        try {
            _clientSocket = new DatagramSocket();
        }
        catch (SocketException ex) {
            throw new ConnectionException("[UDP] " + Constants.SERVER_IOERR + "\n");
        }
    }

    public String receive() throws ConnectionException, SocketTimeoutException {
        byte[] buf = new byte[20];
        DatagramPacket packet;
        packet = new DatagramPacket(buf, buf.length);
        try {
            _clientSocket.receive(packet);
        }
        catch (IOException ex) {
            throw new ConnectionException("[UDP] " + Constants.SOCK_READERR);
        }
        String received = new String(packet.getData(), 0, packet.getLength());
        return received;
    }

    public void send(String message) throws ConnectionException {
        byte[] buf = new byte[1024];
        buf = message.getBytes();
        DatagramPacket packet;
        packet = new DatagramPacket(buf, buf.length, _ipToSend, _portToSend);
        try {
            _clientSocket.send(packet);
        }
        catch (IOException ex) {
            throw new ConnectionException("[UDP] " + Constants.SOCKET_SENDERR);
        }
    }

    public void close() {
        _clientSocket.close();
    }
}
