package centralserver.connection;

import centralserver.Constants;
import centralserver.exceptions.ConnectionException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import static java.net.InetAddress.getByName;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asus
 */
public class ConnectionTCP {
    private String _name; //todo
    private String _ip;
    private Integer _port;
    private InetAddress _address;
    private Socket _socket;
    private PrintWriter _out;
    private BufferedReader _in;

    /**
     *
     * @param name
     * @param port
     * @throws ConnectionException
     */
    public ConnectionTCP(String name, Integer port) throws ConnectionException {
        if (name == null) {
            _name = Constants.DEFAULT_HOST;
        }
        else {
            _name = name;
        }

        if (port == null) {
            _port = Constants.DEFAULT_PORT;
        }
        else {
            _port = port;
        }
        try {
            _address = getByName(_name);
        }
        catch (UnknownHostException ex) {
            throw new ConnectionException(Constants.SOCK_UHOST + _name + "\n");
        }
        _ip = _address.toString();
        createSocket();
    }

    /**
     *
     * @param socket
     * @throws ConnectionException
     */
    public ConnectionTCP(Socket socket) throws ConnectionException {
        _socket = socket;
        _address = _socket.getInetAddress();
        _name = _address.getHostName();
        _port = _socket.getPort();
        _ip = _address.toString();
        createIO();
    }

    private void createSocket() throws ConnectionException {
        try {
            _socket = new Socket(_address, _port);
            createIO();
        }
        catch (UnknownHostException e) {
            throw new ConnectionException(Constants.SOCK_UHOST + _name + "\n");
        }
        catch (IOException e) {
            //e.printStackTrace();
            throw new ConnectionException(Constants.SOCK_IOERR + _name + "\n");
        }
    }

    private void createIO() throws ConnectionException {
        try {
            _out = new PrintWriter(_socket.getOutputStream(), true);
            _in = new BufferedReader( new InputStreamReader(_socket.getInputStream()));
        }
        catch (IOException e) {
            throw new ConnectionException(Constants.SOCK_IOERR + _name + "\n");
        }
    }

    /**
     *
     * @param command
     */
    public void send(String command) {
        _out.print(command);
        _out.flush();
    }

    /**
     *
     * @return
     * @throws ConnectionException
     */
    public String receive() throws ConnectionException {
        try {
            String receivedStr = "";
            String line = _in.readLine();

            while (line != null) {
                receivedStr += line + "\n";
                line = _in.readLine();
            }
            close();
            createSocket();
            return receivedStr;
        }
        catch(IOException e) {
            throw new ConnectionException(Constants.SOCK_READERR);
        }
    }

    /**
     *
     * @return
     * @throws ConnectionException
     */
    public String receiveLine() throws ConnectionException {
        String receivedStr;
        try {
            createIO();
            receivedStr = _in.readLine() + "\n";
            return receivedStr;
        }
        catch(IOException e) {
            //e.printStackTrace();
            throw new ConnectionException(Constants.SOCK_READERR);
        }
    }

    /**
     *
     * @throws ConnectionException
     */
    public void close() throws ConnectionException {
        try {
            _out.close();
            _in.close();
            _socket.close();
        }
        catch(IOException e) {
            throw new ConnectionException(Constants.SOCK_CLOSEERR);
        }
    }

    public String getName() {
        return _name;
    }

    public String getIp() {
        return _ip;
    }

    public Integer getPort() {
        return _port;
    }

}
