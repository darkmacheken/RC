package clientserver;

import java.net.Socket;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.UnknownHostException;


public class ClientTCP {
    private String _csName = ""; //todo
    private int _csPort = 58063;
    private Socket _socket;
    private PrintWriter _out;
    private BufferedReader _in;

    public ClientTCP() {
        this.createSocket();
    }

    public ClientTCP(String csName) {
        _csName = csName;
        this.createSocket();
    }

    public ClientTCP(int csPort) {
        _csPort = csPort;
        this.createSocket();
    }

    public ClientTCP(String csName, int csPort) {
        _csName = csName;
        _csPort = csPort;
        this.createSocket();
    }

    private void createSocket() {
        try {
            _socket = new Socket(_csName, _csPort);
            _out = new PrintWriter(_socket.getOutputStream(), true);
            _in = new BufferedReader( new InputStreamReader(_socket.getInputStream()));
        }
        catch (UnknownHostException e) {
            System.out.println("Don't know about host " + _csName);
        }
        catch (IOException e) {
            System.out.println("Couldn't get I/O for the connection to " + _csName);
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
