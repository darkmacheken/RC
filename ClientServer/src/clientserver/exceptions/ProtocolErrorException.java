/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientserver.exceptions;

/**
 *
 * @author Asus
 */
public class ProtocolErrorException extends ClientException {

    private static final long serialVersionUID = 280920170031L;

    private String _message;

    public ProtocolErrorException(String errorDescription, String message) {
        super(errorDescription);
        _message=message;
    }

    public String getMessage(){
        return _message;
    }

}
