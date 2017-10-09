/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralserver.processing;

/**
 *
 * @author Asus
 */
public class RequestError extends Request {
    private String _error;

    /**
     *
     * @param nameAdress
     * @param iP
     * @param port
     * @param processor
     */
    public RequestError(String nameAdress, String iP, int port, String error, RequestProcessor processor) {
        super(nameAdress, iP, port, processor);
        _error = error;
    }
    
    /**
     *
     * @return
     */
    public String getError() {
        return _error;
    }

    
}
