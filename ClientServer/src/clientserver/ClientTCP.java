package clientserver;

import java.net.Socket;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.UnknownHostException;
import exceptions.ErrorException;


public class ClientTCP {
    private String _csName = ""; //todo
    private Integer _csPort = 58063;
    private Socket _socket;
    private PrintWriter _out;
    private BufferedReader _in;

    public ClientTCP(String csName, Integer csPort) {
        if (csName == NULL) {
            ;
        }
        else {
            _csName = csName;
        }

        if (csPort == NULL) {
            ;
        }
        else {
            _csPort = csPort;
        }

        this.createSocket();
    }

    private void createSocket() throws ErrorException {
        try {
            _socket = new Socket(_csName, _csPort);
            _out = new PrintWriter(_socket.getOutputStream(), true);
            _in = new BufferedReader( new InputStreamReader(_socket.getInputStream()));
        }
        catch (UnknownHostException e) {
            throw new ErrorException(Constants.SOCK_UHOST + _csName + "\n");
        }
        catch (IOException e) {
            throw new ErrorException(Constants.SOCK_IOERR + _csName + "\n");
        }
    }

    public void send(String command) {
        _out.println(command);
    }

    public String receive() {
        String receivedStr = "";
        String tempStr;
        try {
            while ((tempStr = _in.readLine()) != null) {
                receivedStr += tempStr;
            }
        }
        catch(IOException e) {
            System.out.println("Error reading from socket");
        }

        return receivedStr;
    }

}
