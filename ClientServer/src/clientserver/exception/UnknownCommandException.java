package clientserver.exception;

/**
 * Unknown command client server
 */
public class UnknownCommandException extends Exception {

    private static final long serialVersionUID = 1L;
    private String _unknownCommand;
    
    public UnknownCommandException(String unknownCommand){
        _unknownCommand=unknownCommand;
    }
    
    public String getUnknownCommand(){
        return _unknownCommand;
    }
}
