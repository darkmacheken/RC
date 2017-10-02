package centralserver;

import centralserver.exceptions.ConnectionException;
import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;

public class ServerTCP {
    private Integer _port;
    private ServerSocket _serverSocket;

    public ServerTCP(Integer port) {
        if (port == null) {
            _port = Constants.DEFAULT_PORT;
        }
        else {
            _port = port;
        }
        createServerSocket();
    }

    private void createServerSocket() throws ConnectionException {
        try {
            _serverSocket = new ServerSocket(_port);
        }
        catch (IOException e) {
            throw new ConnectionException(Constants.SERVER_IOERR + _port + "\n");
        }
    }

    public ConnectionTCP acceptSocket() throws ConnectionException {
        try {
            Socket clientSocket = serverSocket.accept();
        }
        catch (IOException e) {
            throw new ConnectionException(Constants.SERVER_LISTENERR);
        }
        ConnectionTCP connection = ConnectionTCP(clientSocket);                 //should this be an attribute?
        return connection;
    }

    public void close() throws ConnectionException {
        try {
            _serverSocket.close();                                              //should I close the connection?
        }
        catch(IOException e) {
            throw new ConnectionException(Constants.SERVER_CLOSEERR);
        }
    }

}
