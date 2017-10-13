package centralserver.connection;

import centralserver.Constants;
import centralserver.exceptions.ConnectionException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import static java.net.InetAddress.getByName;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Interface that encapsulates the sockets for connecetion via TCP
 */
public class ConnectionTCP {
    private String _name; //todo
    private String _ip;
    private Integer _port;
    private InetAddress _address;
    private Socket _socket;
    private PrintWriter _out;
    private BufferedReader _in;
    private InputStream _socketInput;

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
     * @throws java.io.IOException
     */
    public ConnectionTCP(Socket socket) throws ConnectionException, IOException {
        _socket = socket;
        _address = _socket.getInetAddress();
        _name = _address.getHostName();
        _port = _socket.getPort();
        _ip = _address.toString();
        _socketInput = socket.getInputStream();
        createIO();
    }

    private void createSocket() throws ConnectionException {
        try {
            _socket = new Socket(_address, _port);
            _socketInput = _socket.getInputStream();
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
        String receivedStr="";
        try {
            int byteRead = _socketInput.read();
            char charRead;
            while(byteRead >= 0){
                charRead = (char) byteRead;
                receivedStr += Character.toString(charRead);
                byteRead = _socketInput.read();
            }
            
            return receivedStr;
        }
        catch(IOException e) {
            //e.printStackTrace();
            throw new ConnectionException(Constants.SOCK_READERR);
        }
    }

    /**
     *
     * @return
     * @throws ConnectionException
     */
    public String receiveLine() throws ConnectionException {
        String receivedStr="";
        try {
            int byteRead = _socketInput.read();
            char charRead = (char) byteRead;
            receivedStr += Character.toString(charRead);
            
            while(charRead != '\n'){
                byteRead = _socketInput.read();
                charRead = (char) byteRead;
                receivedStr += Character.toString(charRead);
            }
            
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
