package user;

/**
 * All constant
 */
public final class Constants {
    //Constant values

    /**
     *
     */
    public static final String DEFAULT_HOST = "127.0.0.1";

    /**
     *
     */
    public static final int DEFAULT_PORT = 58063;

    //Request Error Messages
    /**
     *Message: Nothing received from CS.
     */
    public static final String REQ_NULL = "Nothing received from CS.\n";

    /**
     *Message: Request cannot be answered by the CS.
     */
    public static final String REQ_EOF = "Request cannot be answered by the CS.\n";

    /**
     *Message: Error request to the CS.
     */
    public static final String REQ_ERR = "Error request to the CS.\n";

    //Argument Error Messages
    /**
     *Message: Too few arguments.
     */
    public static final String ARG_LESS = "Too few arguments.\n";

    /**
     *Message: Too many arguments.
     */
    public static final String ARG_HIGH = "Too many arguments.\n";

    /**
     *Message: PTC is not correct.
     */
    public static final String ARG_PTCERR = "PTC is not correct.\n";

    //Files Error Messages
    /**
     *Message: File not found.
     */
    public static final String FILE_NFOUND = "File not found.\n";
    
    /**
     *Message: Can't write to file.
     */
    public static final String FILE_CNTWRT = "Can't write to file.\n";

    //Protocol Error Messages
    /**
     *Message: Message received does't follow the protocol.
     */
    public static final String PT_NFOLLOW = "Message received does't follow the protocol.\n";

    //Socket Error Messages
    /**
     *Message: Unknown host: [hostname]
     */
    public static final String SOCK_UHOST = "Unknown host: ";

    /**
     *Message: Couldn't get I/O for the connection to [hostname]
     */
    public static final String SOCK_IOERR = "Couldn't get I/O for the connection to ";

    /**
     *Message: Error reading from socket
     */
    public static final String SOCK_READERR = "Error reading from socket\n";

    /**
      *Message: Error closing socket
      */
    public static final String SOCK_CLOSEERR = "Error closing socket\n";

    /**
     *Message: Couldn't create server on port [_port]
     */
    public static final String SERVER_IOERR = "Couldn't create server on port ";

    /**
     *Message: Error listening for connections on server
     */
    public static final String SERVER_LISTENERR = "Error listening for connections on server\n";

    /**
      *Message: Error closing server socket
      */
    public static final String SERVER_CLOSEERR = "Error closing server socket\n";

}
