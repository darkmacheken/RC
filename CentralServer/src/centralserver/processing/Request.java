/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralserver.processing;

/**
 *
 * @author Pedro Daniel
 */
public abstract class Request {
    //Client Request identifier
    private final String _nameAdress;
    private final String _iP;
    private final int _port;
    
     //Processor class of the request
    private final RequestProcessor _processor;

    /**
     *
     * @param _nameAdress
     * @param _iP
     * @param _port
     */
    public Request(String _nameAdress, String _iP, int _port, RequestProcessor _processor) {
        this._nameAdress = _nameAdress;
        this._iP = _iP;
        this._port = _port;
        
        this._processor = _processor;
    }

    /**
     *
     * @return
     */
    public String getNameAdress() {
        return _nameAdress;
    }

    /**
     *
     * @return
     */
    public String getiP() {
        return _iP;
    }

    /**
     *
     * @return
     */
    public int getPort() {
        return _port;
    }

    /**
     *
     * @return
     */
    public RequestProcessor getProcessor() {
        return _processor;
    }
     
}
