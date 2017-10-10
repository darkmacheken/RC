/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralserver;

import centralserver.connection.ServerTCP;
import centralserver.exceptions.ConnectionException;
import centralserver.threads.ServerTCPThread;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asus
 */
public class CS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Integer cSPort = null;
        
        try {
            for (int i = 0; i < args.length; i++) {
                if (args[i].equals("-p")) {
                    i++;
                    cSPort = Integer.parseInt(args[i]);
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Erro de parâmetros.\n");
            return;
        }
        
        try {
            WSList list = new WSList();
            ServerTCP server = new ServerTCP(cSPort);
            System.out.println("Server created.");
            ServerTCPThread thread = new ServerTCPThread(list, server);
            thread.join();
        }
        catch (ConnectionException e) {
            System.out.println(e.getErrorDescription());
        }
        catch (InterruptedException e) {
            ;
        }
    }
    
}
