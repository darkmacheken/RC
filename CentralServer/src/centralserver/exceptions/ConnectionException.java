/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralserver.exceptions;

/**
 *Error Exception
 */
public class ConnectionException extends Exception {

    private static final long serialVersionUID = 270920172254L;

    private String _errorDescription;

    public ConnectionException(String errorDescription) {
        _errorDescription=errorDescription;
    }

    public String getErrorDescription() {
        return _errorDescription;
    }

}
