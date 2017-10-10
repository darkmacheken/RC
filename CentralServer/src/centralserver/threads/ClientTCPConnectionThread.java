/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralserver.threads;

import centralserver.exceptions.ConnectionException;
import centralserver.processing.request.RequestToWS;

/**
 *
 * @author duartegalvao
 */
public class ClientTCPConnectionThread extends Thread {
    private RequestToWS _request;
    
    public ClientTCPConnectionThread(RequestToWS request) {
        
    }
    
    @Override
    public void run() {
        try {
            _request.process(null);
        } catch (ConnectionException e) {
            // never reaches here
        }
    }
}
