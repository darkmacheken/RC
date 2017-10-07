package clientserver;

import clientserver.exceptions.ConnectionException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class ConnectionTCP {
    private String _name; //todo
    private String _ip;
    private Integer _port;
    private Socket _socket;
    private PrintWriter _out;
    private BufferedReader _in;

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
            //System.out.println("Socket created and connected to " + _name + ":" + _port);
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

    public void send(String command) {
        _out.print(command);
        _out.flush();
    }

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
            e.printStackTrace();
            throw new ConnectionException(Constants.SOCK_READERR);
        }            
    }

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

}
