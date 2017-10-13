/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.exceptions;

/**
 *
 * @author Asus
 */
public class ProtocolErrorException extends ConnectionException {

    private static final long serialVersionUID = 280920170031L;

    private String _message;

    /**
     *
     * @param errorDescription
     * @param message
     */
    public ProtocolErrorException(String errorDescription, String message) {
        super(errorDescription);
        _message=message;
    }

    public String getMessage(){
        return _message;
    }

}
