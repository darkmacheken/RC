package workingserver.connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import workingserver.Constants;
import workingserver.exceptions.ConnectionException;

/**
 *
 *  
 */
public class ServerTCP {
    private Integer _port;
    private ServerSocket _serverSocket;

    /**
     *
     * @param port
     * @throws ConnectionException
     */
    public ServerTCP(Integer port) throws ConnectionException {
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

    /**
     *
     * @return
     * @throws ConnectionException
     * @throws java.io.IOException
     */
    public ConnectionTCP acceptSocket() throws ConnectionException, IOException {
        Socket clientSocket;
        try {
            clientSocket = _serverSocket.accept();
        }
        catch (IOException e) {
            throw new ConnectionException(Constants.SERVER_LISTENERR);
        }
        ConnectionTCP connection = new ConnectionTCP(clientSocket);                 //should this be an attribute?
        return connection;
    }

    /**
     *
     * @throws ConnectionException
     */
    public void close() throws ConnectionException {
        try {
            _serverSocket.close();                                              //should I close the connection?
        }
        catch(IOException e) {
            throw new ConnectionException(Constants.SERVER_CLOSEERR);
        }
    }
}
