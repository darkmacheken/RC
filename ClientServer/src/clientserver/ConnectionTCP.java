package clientserver;

import clientserver.exceptions.ConnectionException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Asus
 */
public class ConnectionTCP {
    private String _name; //todo
    private String _ip;
    private Integer _port;
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

        createSocket();
    }

    /**
     *
     * @param socket
     * @throws ConnectionException
     */
    public ConnectionTCP(Socket socket) throws ConnectionException {
        _socket = socket;
        _name = _socket.getInetAddress().getHostName();
        _ip = _socket.getInetAddress().getHostAddress();
        _port = _socket.getPort();
        createIO();
    }

    private void createSocket() throws ConnectionException {
        try {
            _socket = new Socket(_name, _port);
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
    
    //getters

    /**
     *
     * @return
     */
     public String getName() {
        return _name;
    }

    /**
     *
     * @return
     */
    public String getIp() {
        return _ip;
    }

    /**
     *
     * @return
     */
    public Integer getPort() {
        return _port;
    }

}
