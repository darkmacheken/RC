/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralserver;

/**
 *
 * @author Pedro Daniel
 */
public class ConnectAddress {
    private final String _ip;
    private final int port;

    /**
     *
     * @param _ip
     * @param port
     */
    public ConnectAddress(String _ip, int port) {
        this._ip = _ip;
        this.port = port;
    }

    /**
     *
     * @return
     */
    public String getIp() {
        return _ip;
    }

    /**
     *
     * @return
     */
    public int getPort() {
        return port;
    }
    
    
    
}
