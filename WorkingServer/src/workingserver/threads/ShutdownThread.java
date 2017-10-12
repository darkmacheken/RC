/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workingserver.threads;

import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import workingserver.connection.ConnectionUDP;
import workingserver.exceptions.ConnectionException;

/**
 *
 * @author Leo
 */
public class ShutdownThread extends Thread {

    private ConnectionUDP _connection;
    private Integer _wsPort;
    
    public ShutdownThread(ConnectionUDP connection, Integer wsPort) {
        _wsPort = wsPort;
        _connection = connection;
    }
    
    @Override
    public void run() {
        String unregMessage = "UNR ";
        try {
            unregMessage = unregMessage + InetAddress.getLocalHost().getHostAddress() + " " + _wsPort + "\n";
        } catch (UnknownHostException e) {
            System.out.println("Error: Unknown host.\n");
        }
                int counter = 0;
                while(counter < 3){
                    try{
                        _connection.send(unregMessage);
                        String received = _connection.receive();
                        if(received.equals("UAK OK\n"))
                            break;
                        counter++;
                    }
                    catch(SocketTimeoutException e){
                        counter++;
                    }
                    catch(ConnectionException e) {
                        System.err.println(e.getErrorDescription());
                    }
                }
                System.out.println("Server registered succesfully in CS: " + _connection.getNameToSend() + " " + _connection.getPortToSend());
            }
    
}
