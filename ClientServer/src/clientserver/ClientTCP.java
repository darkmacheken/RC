package clientserver;

import java.net.Socket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.IOException;

public class ClientTCP {
    private String _csName = ""; //FIX
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
            showText("Don't know about host " + csName);
        }
        catch (IOException e) {
            showText("Couldn't get I/O for the connection to " + csName);
        }
    }

    public void send(String command) {
        _out.println(command);
    }

    public String receive() {
        String receivedStr = "";
        String tempStr;
        while ((tempStr = _in.readline()) != null) {
            receivedStr += tempStr;
        }
        return receivedStr;
    }

}
