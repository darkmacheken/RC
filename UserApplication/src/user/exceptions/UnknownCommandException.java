package user.exceptions;

/**
 * Unknown command client server
 */
public class UnknownCommandException extends Exception {

    private static final long serialVersionUID = 270920172255L;
    private String _unknownCommand;

    /**
     *
     * @param unknownCommand
     */
    public UnknownCommandException(String unknownCommand){
        _unknownCommand=unknownCommand;
    }

    @Override
    public String toString(){
        return _unknownCommand;
    }
}
