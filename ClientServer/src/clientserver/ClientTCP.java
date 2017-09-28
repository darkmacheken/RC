package clientserver;

import clientserver.exceptions.ClientException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class ClientTCP {
    private String _csName; //todo
    private Integer _csPort;
    private Socket _socket;
    private PrintWriter _out;
    private BufferedReader _in;

    public ClientTCP(String csName, Integer csPort) throws ClientException {
        if (csName == null) {
            _csName = Constants.DEFAULT_HOST;
        }
        else {
            _csName = csName;
        }

        if (csPort == null) {
            _csPort = Constants.DEFAULT_PORT;
        }
        else {
            _csPort = csPort;
        }

        this.createSocket();
    }

    private void createSocket() throws ClientException {
        try {
            _socket = new Socket(_csName, _csPort);
            System.out.println("Socket created and connected to " + _csName + ":" + _csPort);
            _out = new PrintWriter(_socket.getOutputStream(), true);
            _in = new BufferedReader( new InputStreamReader(_socket.getInputStream()));
        }
        catch (UnknownHostException e) {
            throw new ClientException(Constants.SOCK_UHOST + _csName + "\n");
        }
        catch (IOException e) {
            throw new ClientException(Constants.SOCK_IOERR + _csName + "\n");
        }
    }

    public void send(String command) {
        _out.println(command);
    }

    public String receive() throws ClientException {
        String receivedStr = "";
        String tempStr;
        try {
            while ((tempStr = _in.readLine()) != null) {
                receivedStr += tempStr;
            }
        }
        catch(IOException e) {
            throw new ClientException(Constants.SOCK_READERR);
        }

        return receivedStr;
    }

    public void close() throws ClientException {
        try {
            _out.close();
            _in.close();
            _socket.close();
        }
        catch(IOException e) {
            throw new ClientException(Constants.SOCK_CLOSEERR);
        }
    }

}
