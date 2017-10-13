/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.exceptions;

/**
 *Error Exception
 */
public class ConnectionException extends Exception {

    private static final long serialVersionUID = 270920172254L;

    private String _errorDescription;

    /**
     *
     * @param errorDescription
     */
    public ConnectionException(String errorDescription) {
        _errorDescription=errorDescription;
    }

    /**
     *
     * @return
     */
    public String getErrorDescription() {
        return _errorDescription;
    }

}
